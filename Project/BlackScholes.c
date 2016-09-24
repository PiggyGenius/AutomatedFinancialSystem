#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// Delta : Amount an option price is expected to move based on a 1$ change in the underlying stock.
//         When maturity is near, delta of in-the-money option --> 1 : Call option.
//         When maturity is near, delta of in-the-money option -> -1 : Put option.
//         Not mathematically correct but delta can be seen as a probability to be in-the-money.
//         Delta can be seen as the speed at which an option price changes.
//
// Gamma : Gamma can be see as the accelration at which an option price changes.
//         It is the rate at which delta will change based on a 1$ change in the stock price.
//         If gamma is high then the option's price is really responsive to changes in the price of the stock.
//         High gamma means that the value of the option will change rapidly.
//
// Vega  : Amount option prices will change for a one-point change in implied volatility.
//         Vega doesn't affect the value of options but it affects the time value of an option's price.
//         Typically the value of an option will increase as the implied volatility increases.
//         Increase implied volatility -----> increase range of potential movement for the stock.
//
// Theta : Theta is the time decay, the amount the price of the option will decrease for a one-day change in the time to expiration.
//         Theta is lower for out-of-money options than at-the-money options, loss may still be greater in percentage.
//         At-the-money options experience more significant dollar losses over time that in or out-the-money options.
//
// Rho   : Amount an option value will change based on a one percentage-point change in interest rates.
//         It's value is more important in long-term options than short-term options.
//         More information needed.
//
// Missing a few indicators, will get updated.
struct greeks_s {
    double delta;
    double gamma;
    double vega;
    double theta;
    double rho;
    double vanna;
}; typedef struct greeks_s greeks;


// This function is the standard normal cumulative distribution function
// Go on wiki page of Black-Scholes for more information.
/*
double N(double z){
    const double b1=0.31938153;
    const double b2=-0.356563782;
    const double b3=1.781477937;
    const double b4=-1.821255978;
    const double b5=1.330274429;
    const double p=0.2316419;

    double a=fabs(z);
    double t=1.0/(1.0+a*p);
    double w=1.0-1.0/sqrt(2*M_PI)*exp(-a*a/2)*(b1*t+b2*t*t+b3*pow(t,3)+b4*pow(t,4)+b5*pow(t,5));
    if(z<0.0)
        w=1.0-w;
    return w;
}*/

double N(double z){
    return 0.5+0.5*erf(z/sqrt(2));
}

// This is the standard normal probability density function
double N_density(double z){
    return (1/(sqrt(2*M_PI)))*exp(-(pow(z,2)/2));
}

// Go on wiki page of Black-Scholes_model to understand this formula
double d1(double S, double K, double r, double T, double vol){
    return (log(S/K)+(r+pow(vol,2)/2)*T)/(vol*sqrt(T));
}


// Gon on wiki page of Black-Scholes_model to understand this formula
double d2(double S, double K, double r, double T, double vol){
    return d1(S,K,r,T,vol)-vol*sqrt(T);
}

// Go on wiki page of Black-Scholes_model for more information.
// The callFlag : equals to 1 in case of a call option and -1 in case of a put option
// The S variable : equals to the price of the asset, a stock.
// The K variable : equals to the price of the strike, call or put.
// The r variable : equals to risk free rate. TBD.
// The vol variable : equals to the volatility. TBD.
// The T variable : equals to the number of days left until maturity (T-t).
void black_scholes(double flag, double S, double K, double r, double vol, double T){
    r=r/100;
    T=T/365;

    double x=S*N(flag*d1(S,K,r,T,vol));
    double y=K*exp(-1*r*T)*N(flag*d2(S,K,r,T,vol));
    if(flag==1){
        printf("\nThe price of the call is : %lf\n\n",flag*(x-y));
    }
    else if(flag==-1){
        printf("\nThe price of the put is  : %lf\n\n",flag*(x-y));
    }
}
// Values might be off, the standard normal cumulative distribution function needs to get checked !!!
void black_scholes_greeks(double S, double K, double r, double vol, double T, greeks* call_greek_values, greeks* put_greek_values){
    r=r/100;
    T=T/365;

    call_greek_values->delta=N(d1(S,K,r,vol,T));
    put_greek_values->delta=-N(-d1(S,K,r,vol,T));

    call_greek_values->gamma=(N_density(d1(S,K,r,vol,T)))/(S*vol*sqrt(T));
    //put_greek_values->gamma=(N_density(d1(S,K,r,vol,T)))/(S*vol*sqrt(T));
    put_greek_values->gamma=call_greek_values->gamma;

    call_greek_values->vega=S*N_density(d1(S,K,r,vol,T))*sqrt(T);
    //put_greek_values->vega=S*N_density(d1(S,K,r,vol,T))*sqrt(T);
    put_greek_values->vega=call_greek_values->vega;
    
    call_greek_values->theta=-((S*N_density(d1(S,K,r,vol,T))*vol)/(2*sqrt(T)))-(r*K*exp(-r*T)*N(d2(S,K,r,vol,T)));   
    put_greek_values->theta=-((S*N_density(d1(S,K,r,vol,T))*vol)/(2*sqrt(T)))+(r*K*exp(-r*T)*N(-d2(S,K,r,vol,T)));
    
    call_greek_values->rho=K*T*exp(-r*T)*N(d2(S,K,r,vol,T));
    put_greek_values->rho=-K*T*exp(-r*T)*N(-d2(S,K,r,vol,T));

    ///////////NEW SHIT RIGHT HERE\\\\\\\\\\\\

    call_greek_values->vanna=-N_density(d1(S,K,r,vol,T))*(d2(S,K,r,vol,T)/vol);
    put_greek_values->vanna=call_greek_values->vanna;

    call_greek_values->charm=N(d1(S,K,r,vol,T))-(N_density(d1(S,K,r,vol,T))*((2*r*T-d2(S,K,r,vol,T)*vol*sqrt(T)))/(2*T*vol*sqrt(T)));
    put_greek_values->charm=N(-d1(S,K,r,vol,T))-(N_density(d1(S,K,r,vol,T))*((2*r*T-d2(S,K,r,vol,T)*vol*sqrt(T)))/(2*T*vol*sqrt(T))); // peut être moins N.
}

// Volatility : Amount the stock price fluctates, highs and lows of every day during a year.
// Historical volatility : What happened in the past, the annualized standard deviation of stock price movement.
// Implied volatility : What the marketplace is implying the stock volatility to be in the future based on price changes on an option.
//                      If there is no option traded on a specific stock, there is no way to know the implied volatility of this stock.
//                      It's an easy way to take in account events, they will change the price of option contracts.
//
// When implied volatility changes, the price of options changes as well in the same direction.
// It is a percentage of the stock price, based on general consensus in the market place.
// A stock should end up 68% of the time within one standard derivation of its original price 68% of the time during the next 12 months.
// 95% of the time within two standard derivations and 99% percent of the time within three standard derivations.
// Potential range of movement for a stock. Should use log normal distribution, might change it.
//
// Standard derivation of option : (stock_price*implied_volatility*sqrt(days_to_expiration))/sqrt(365)
//
// Option price : stock price, strike price, expiration date, interest rate, dividends, implied volatility
int main(void){
    printf("Test of Black-Scholes model, experimental phase\n\n");
    double callflag, S, K, r, vol, days;
    printf("-1 if option is a put or 1 if option is a call : ");
    scanf("%lf",&callflag);
    printf("Stock price : ");
    scanf("%lf",&S);
    printf("Strike price : ");
    scanf("%lf",&K);
    printf("Risk free rate as a percentage : ");
    scanf("%lf",&r);
    printf("Volatility : ");
    scanf("%lf",&vol);
    printf("Time to expiration in days : ");
    scanf("%lf",&days);
    black_scholes(callflag,S,K,r,vol,days);

    greeks call_greek_values;
    greeks put_greek_values;
    black_scholes_greeks(S,K,r,vol,days,&call_greek_values,&put_greek_values);
    printf("Call\n\nDelta : %lf\nGamma : %lf\nVega : %lf\nTheta : %lf\nRho : %lf\nVanna : %lf\n\n",call_greek_values.delta,call_greek_values.gamma,call_greek_values.vega,call_greek_values.theta,call_greek_values.rho,call_greek_values.vanna);
    printf("Put\n\nDelta : %lf\nGamma : %lf\nVega : %lf\nTheta : %lf\nRho : %lf\nVanna : %lf\n",put_greek_values.delta,put_greek_values.gamma,put_greek_values.vega,put_greek_values.theta,put_greek_values.rho,put_greek_values.vanna);
    return 0;
}

