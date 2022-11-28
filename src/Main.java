import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final char page[][] = new char[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                page[i][j] = ' ';
            }
        }
        boolean turn = false;
        page[0][1] = page[1][0] = '1';
        page[0][2] = page[2][0] = '2';
        page[0][3] = page[3][0] = '3';
        page[0][4] = '4';
        ShowPage(page);
        while (true) {
            if (!turn) {
                while (true) {
                    try {
                        System.out.print("Choose one:");
                        final String str = input.nextLine();
                        final int row = Integer.valueOf("" + str.charAt(0));
                        final int column = Integer.valueOf("" + str.charAt(2));
                        if (page[row][column] == ' ') {
                            page[row][column] = '+';
                        } else {
                            System.out.println("Error");
                            continue;
                        }
                        turn = true;
                        break;
                    } catch (Exception ex) {
                        System.out.println("Error");
                    }
                }
                final char check = CheckGame(page);
                if (check == '+') {
                    System.out.println("Win juego: + ");
                    break;
                } else if (check == '*') {
                    System.out.println("Win juego: *");
                    break;
                }
            } else {
                ShowPage(page);
                final String win=Win(page,'*');
                final String lose=Win(page,'+');
                if (win!=null){
                    final int row = Integer.valueOf("" + win.charAt(0));
                    final int column = Integer.valueOf("" + win.charAt(2));
                    page[row][column] = '*';
                } else if (lose!=null){
                    final int row = Integer.valueOf("" + lose.charAt(0));
                    final int column = Integer.valueOf("" + lose.charAt(2));
                    page[row][column] = '*';
                } else if (page[2][2]=='+'&&page[2][3]==' '){
                    page[2][3]='*';
                } else if (page[2][3]=='+'&&page[2][2]==' '){
                    page[2][2]='*';
                }else if (page[2][2]==' '){
                    page[2][2]='*';
                }else if (page[2][3]==' '){
                    page[2][3]='*';
                }
                ShowPage(page);
                final char check = CheckGame(page);
                if (check == '+') {
                    System.out.println("Win juego: +");
                    break;
                } else if (check == '*') {
                    System.out.println("Win juego: *");
                    break;
                }
                turn=false;
            }
        }
    }
    public static void ShowPage(char page[][]){
        System.out.println("//////////////////////////////////");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5 ; j++) {
                System.out.print(""+page[i][j]+" ");
            }
            System.out.println("");
        }
    }
    public static String Win(char page[][], char next) {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 5; j++) {
                if (page[i][j]==' '){
                    page[i][j]=next;
                    final char check=CheckGame(page);
                    page[i][j]=' ';
                    if (check==next){
                        return i+" "+j;
                    }
                }
            }
        }
        return null;
    }
    public static char CheckGame(char page[][]) {
        for (int i = 1; i < 4; i++) {
            for (int j = 2; j < 4; j++) {
                if (page[i][j-1] == page[i][j] && page[i][j-1] == page[i][j+1]) {
                    if (page[i][j] == '+') {
                        return '+';
                    } else if (page[i][j] == '*') {
                        return '*';
                    }
                }
            }
        }
        for (int i = 1; i < 5; i++) {
            if (page[1][i] == page[2][i] && page[1][i] == page[3][i]) {
                if (page[1][i] == '+') {
                    return '+';
                } else if (page[1][i] == '*') {
                    return '*';
                }
            }
        }
        for (int i = 1; i < 3; i++) {
            if (page[1][i] == page[2][i+1] && page[1][i] == page[3][i + 2]) {
                if (page[1][i] == '+') {
                    return '+';
                } else if (page[1][i] == '*') {
                    return '*';
                }
            }
        }
        for (int i = 4; i > 2; i--) {
            if (page[1][i] == page[2][i-1] && page[1][i] == page[3][i - 2]) {
                if (page[1][i] == '+') {
                    return '+';
                } else if (page[1][i] == '*') {
                    return '*';
                }
            }
        }
        return ' ';
    }
}
