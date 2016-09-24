public class Main {
    public static void main(String[] args){
        DataAccess model = new DataAccess();
        String[][] test = model.getClientsInformation();
        String[] testou = model.getClientInformation(1);
        for(int i=0;i<test.length;i++){
            for(int j=0;j<test[i].length;j++){
                System.out.print(test[i][j]+" ");
            }
            System.out.println();
        }
        test = model.getStocksOfClient(1);
        for(int i=0;i<test.length;i++){
            for(int j=0;j<test[i].length;j++){
                System.out.print(test[i][j]+" ");
            }
            System.out.println();
        }
        for(int i=0;i<test.length;i++){
            System.out.print(testou[i]+" ");
        }
        System.out.println();
    }
}
