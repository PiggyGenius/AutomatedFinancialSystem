#define CURL_STATICLIB
#include <stdio.h>
#include <curl/curl.h>
#include <curl/easy.h>
#include <string.h>

size_t write_data(void* ptr,size_t size,size_t nmemb,FILE* stream){
    size_t written;
    written=fwrite(ptr,size,nmemb,stream);
}

int main(void){
    CURL* curl;
    FILE* fp;
    CURLcode res;
    char* url="http://quote.yahoo.com/d/quotes.csv?s=YHOO+GOOG+MSFT&f=sl1d1t1c1hgvbap2";
    //char* url="https://marketviewer.equiduct.com";
    char outfilename[FILENAME_MAX]="test.csv";
    curl=curl_easy_init();
    if(curl){
        fp=fopen(outfilename,"wb");
        curl_easy_setopt(curl,CURLOPT_URL, url);
        curl_easy_setopt(curl,CURLOPT_WRITEFUNCTION,write_data);
        curl_easy_setopt(curl,CURLOPT_WRITEDATA,fp);
        res=curl_easy_perform(curl);
        curl_easy_cleanup(curl);
        fclose(fp);
    }
    else {
        printf("Error !!!\n");
    }
    return 0;
}
