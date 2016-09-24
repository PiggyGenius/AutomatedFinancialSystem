#include <curl/curl.h>

int main(void){
    CURLcode ret;
    CURL* hnd;

    hnd=curl_easy_init();
    curl_easy_setopt(hnd,CURLOPT_URL,"http://download.finance.yahoo.com/d/quotes.csv?s=YHOO+GOOG+MSFT&f=sl1d1t1c1hgvbap2");
    curl_easy_setopt(hnd,CURLOPT_NOPROGRESS,1L);
    curl_easy_setopt(hnd,CURLOPT_USERAGENT,"curl/7.35.0");
    curl_easy_setopt(hnd,CURLOPT_MAXREDIRS,50L);
    curl_easy_setopt(hnd,CURLOPT_TCP_KEEPALIVE,1L);

    ret=curl_easy_perform(hnd);
    curl_easy_cleanup(hnd);
    hnd=NULL;
    return (int) ret;
}
