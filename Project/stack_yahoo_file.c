#include <curl/curl.h>

size_t Write_Data(void* ptr,size_t size,size_t nmemb,FILE* stream){
    size_t written;
    written=fwrite(ptr,size,nmemb,stream);
}

int main(void){
    CURLcode ret;
    CURL* hnd;
    char outfilename[FILENAME_MAX]="stack_yahoo.txt";
    
    hnd=curl_easy_init();

    FILE* fp;
    fp=fopen(outfilename,"wb");

    curl_easy_setopt(hnd,CURLOPT_URL,"http://download.finance.yahoo.com/d/quotes.csv?s=YHOO+GOOG+MSFT&f=sl1d1t1c1hgvbap2");
    curl_easy_setopt(hnd,CURLOPT_NOPROGRESS,1L);
    curl_easy_setopt(hnd,CURLOPT_USERAGENT,"curl/7.35.0");
    curl_easy_setopt(hnd,CURLOPT_MAXREDIRS,50L);
    curl_easy_setopt(hnd,CURLOPT_TCP_KEEPALIVE,1L);

    curl_easy_setopt(hnd,CURLOPT_WRITEFUNCTION,Write_Data);
    curl_easy_setopt(hnd,CURLOPT_WRITEDATA,fp);

    ret=curl_easy_perform(hnd);
    curl_easy_cleanup(hnd);
    fclose(fp);
    hnd=NULL;
    return (int) ret;
}
