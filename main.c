#include <stdio.h>
#include <stdlib.h>
#include <time.h>

char mapa[6][6];
int wumpus = 2;
int poco = 4;
int ouro = 3;
int morcego = 2;
int posicaox = 0;
int posicaoy = 0;

void define_mapa() {
    int i,j;
    int x = 0;
    int y = 0;
    
    for(i=0; i<6; i++) {
        for(j=0; j<6; j++) {
            if(i==5 && j == 0){
                mapa[i][j] = 'i';
            }
            else if(i==4 && j==0)
                mapa[i][j] = 'L';
            else if(i==5 && j==1)
                mapa[i][j] = 'L';
            else
                mapa[i][j] = '_';
        }
    }
    
     while(wumpus!=0 || poco!=0 || ouro!=0 || morcego !=0){
        srand( (unsigned)time(NULL) );
        x = rand()%6;
        y = rand()%6;
        
        if(mapa[x][y]=='_'){
            if(wumpus!=0){
                mapa[x][y]='W';
                wumpus--;
            }
            else if(poco!=0){
                mapa[x][y]='P';
                poco--;
            }
            else if(ouro!=0){
                mapa[x][y]='O';
                ouro--;
            }
            else if(morcego !=0){
                mapa[x][y]='M';
                morcego--;
            }
        }
    }
    
    for(i=0;i<6;i++)
        for(j=0;j<6;j++){
            if(mapa[i][j] == 'L')
                mapa[i][j] = '_';
        }
}

int main()
{
    int i;
    int j;
    
    define_mapa();
    
    for(i=0;i<6;i++) {
        for(j=0;j<6;j++) {
            printf("%c ",mapa[i][j]);
        }
        printf("\n");
    }

    return 0;
}
