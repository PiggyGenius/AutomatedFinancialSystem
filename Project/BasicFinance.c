#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// simple conversion, give a number of days and get a number of years
double Days_To_Year(double days){
    return days/360;
}

// returns the return on investment rate
double Rate_Of_Return(double P0,double Pt,double I){
    return (Pt-P0+I)/P0;
}

// returns how many years for P0 to become profitable
double Time_Of_Return(double rate_of_return,double P0){
    return 1/rate_of_return;
}

// simple conversion, give a number of years and get a number of days
double Year_To_Days(double year){
    return year*360;
}

// n is the number of years, k is the initial amount and r is the simple-interest-rate
double Interest_Value(double K,double r,double n){
    return K*pow(1+r,n)-K;
}

// give back the total interest rate after n years, p is how many times a year the interest rate is applied
double Compounded_Interest_Rate(double r,double p,double n){
    return pow(1+r,n/p)-1;
}
// give back the interest rate per year on a period based interest rate, p is the year rate in a period
double Annual_Interest_Rate(double r,double p){
    return pow(1+r,1/p)-1;
}

// returns the present value of an amount payed or received at time T, r is the interest rate
double Present_Value(double C,double r,double T){
    return C/pow(1+r,T);
}

// C0 is the cost of the investment, r is the interest rate per year
double Project_Net_Present_Value(double C0,double financial_flow[],double r){
    int i;
    double result=0.0;
    for(i=0;financial_flow[i]!='\0';i++)
        result+=financial_flow[i]/pow((1+r),i+1);
    return result-C0;
}

// same as Project_Net_Present_Value without taking into the calculus the initial cost of the project
double Project_Present_Value(double financial_flow[],double r){
    int i;
    double result=0.0;
    for(i=0;financial_flow[i]!='\0';i++)
        result+=financial_flow[i]/pow((1+r),i+1);
    return result;
}

double Internal_Rate_Of_Return(){
    return 0.0;
}

// used to know in how many years will buying a stock be profitable if the net_profit_per_stock never changes
double Price_Earnings_Ratio(double stock_price,double net_profit_per_stock){
    return stock_price/net_profit_per_stock;
}

// period must be in format DD/MM/YYYY.
double Get_Days_In_Period(char* from_date,char* to_date){
    double from_year=(double) (((from_date[6]-'0')*pow(10,3))+((from_date[7]-'0')*pow(10,2))+((from_date[8]-'0')*10)+(from_date[9])-'0');
    double to_year=(double) (((to_date[6]-'0')*pow(10,3))+((to_date[7]-'0')*pow(10,2))+((to_date[8]-'0')*10)+(to_date[9])-'0');
    double from_month=(double) (((from_date[3]-'0')*10)+(from_date[4]-'0'));
    double to_month=(double) (((to_date[3]-'0')*10)+(to_date[4]-'0'));
    double from_day=(double) (((from_date[0]-'0')*10)+(from_date[1]-'0'));
    double to_day=(double) (((to_date[0]-'0')*10)+(to_date[1]-'0'));

    double N=to_year-from_year;
    double M=(to_month-from_month)/12;
    double J=(to_day-from_day)/360;
    return Year_To_Days(N+M+J);
}

// period must be in format DD/MM/YYYY
double Get_Year_Rate_In_Period(char* from_date,char* to_date){
    double from_year=(double) (((from_date[6]-'0')*pow(10,3))+((from_date[7]-'0')*pow(10,2))+((from_date[8]-'0')*10)+(from_date[9])-'0');
    double to_year=(double) (((to_date[6]-'0')*pow(10,3))+((to_date[7]-'0')*pow(10,2))+((to_date[8]-'0')*10)+(to_date[9])-'0');
    double from_month=(double) (((from_date[3]-'0')*10)+(from_date[4]-'0'));
    double to_month=(double) (((to_date[3]-'0')*10)+(to_date[4]-'0'));
    double from_day=(double) (((from_date[0]-'0')*10)+(from_date[1]-'0'));
    double to_day=(double) (((to_date[0]-'0')*10)+(to_date[1]-'0'));

    double N=to_year-from_year;
    double M=(to_month-from_month)/12;
    double J=(to_day-from_day)/360;
    return (N+M+J);
}


int main(void){
    double K=2000, r=0.05, n=1.0;
    n=Days_To_Year(2160);
    printf("First Value : %lf\nCompounded Interest rate : %lf\nAnnual Interest rate : %lf\n",Interest_Value(K,r,n),Compounded_Interest_Rate(r,0.5,2),Annual_Interest_Rate(r,0.5));
    n=Year_To_Days(n);
    n=Days_To_Year(n);
    printf("Second Value : %lf\nCompounded Interest rate : %lf\nAnnual Interest rate : %lf\nCompounded Interest rate : %lf\n",Interest_Value(K,r,n),Compounded_Interest_Rate(r,0.5,2),Annual_Interest_Rate(r,0.5),Compounded_Interest_Rate(Annual_Interest_Rate(r,0.5),1.0,2));
    printf("\nValues !!!\n\n");
    printf("Present value : %lf\n",Present_Value(1000000,0.05,Days_To_Year(90)));
    printf("Days : %lf\n",Get_Days_In_Period("30/11/2008","01/03/2010"));
    printf("Rate : %lf\n",Annual_Interest_Rate(0.10,Get_Year_Rate_In_Period("30/11/2008","01/03/2010"))*100);
    K=Rate_Of_Return(1000,1250,0);
    r=Time_Of_Return(K,1000);
    printf("Rate of Return : %lf\nTime of Return : %lf\n",K,r);
    double flows[4]={100.0,-150.0,200.0,500.0};
    printf("Net Present Value : %lf\n",Project_Net_Present_Value(400,flows,0.10));
    return 0;
}
