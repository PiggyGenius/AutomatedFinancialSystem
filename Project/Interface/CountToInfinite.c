#include <stdio.h>
#include <stdlib.h>

int main(void){
    int i;
    for(i=0;;i++){
        printf("%d-",i);
        fflush(stdout);
        sleep(1);
    }
    return 0;
}
