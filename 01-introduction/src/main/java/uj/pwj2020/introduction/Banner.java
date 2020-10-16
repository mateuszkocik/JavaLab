package uj.pwj2020.introduction;

public class Banner {

    public String[] toBanner(String input) {
        if(input != null){
            String[] result = {"","","","","","",""};
            String inputInUpper = input.toUpperCase();
            String[] letters = new String[input.length()];

            for(int i = 0; i < inputInUpper.length(); i++){
                String[] letter = getLetter(inputInUpper.charAt(i));
                for(int j = 0; j < 7; j++){
                    result[j] = result[j] + letter[j] + " ";
                }
            }

            for(int i = 0; i < 7; i++){
                result[i]  = result[i].stripTrailing();
            }

            return result;
        }

        return new String[0];
    }

    private String[] getLetter(char letter){
        switch (letter){
            case 'A' : {
                String[] result  = {
                        "   #   ",
                        "  # #  ",
                        " #   # ",
                        "#     #",
                        "#######",
                        "#     #",
                        "#     #"};
                return result;
            }
            case 'B' : {
                String[] result  = {
                        "###### ",
                        "#     #",
                        "#     #",
                        "###### ",
                        "#     #",
                        "#     #",
                        "###### "};
                return result;
            }
            case 'C' : {
                String[] result  = {
                        " ##### ",
                        "#     #",
                        "#      ",
                        "#      ",
                        "#      ",
                        "#     #",
                        " ##### "};
                return result;
            }
            case 'D' : {
                String[] result  = {
                        "###### ",
                        "#     #",
                        "#     #",
                        "#     #",
                        "#     #",
                        "#     #",
                        "###### "};
                return result;
            }
            case 'E' : {
                String[] result  = {
                        "#######",
                        "#      ",
                        "#      ",
                        "#####  ",
                        "#      ",
                        "#      ",
                        "#######"};
                return result;
            }
            case 'F' : {
                String[] result  = {
                        "#######",
                        "#      ",
                        "#      ",
                        "#####  ",
                        "#      ",
                        "#      ",
                        "#      "};
                return result;
            }
            case 'G' : {
                String[] result  = {
                        " ##### ",
                        "#     #",
                        "#      ",
                        "#  ####",
                        "#     #",
                        "#     #",
                        " ##### "};
                return result;
            }
            case 'H' : {
                String[] result  = {
                        "#     #",
                        "#     #",
                        "#     #",
                        "#######",
                        "#     #",
                        "#     #",
                        "#     #"};
                return result;
            }
            case 'I' : {
                String[] result  = {
                        "###",
                        " # ",
                        " # ",
                        " # ",
                        " # ",
                        " # ",
                        "###"};
                return result;
            }
            case 'J' : {
                String[] result  = {
                        "      #",
                        "      #",
                        "      #",
                        "      #",
                        "#     #",
                        "#     #",
                        " ##### "};
                return result;
            }
            case 'K' : {
                String[] result  = {
                        "#    #",
                        "#   # ",
                        "#  #  ",
                        "###   ",
                        "#  #  ",
                        "#   # ",
                        "#    #"};
                return result;
            }
            case 'L' : {
                String[] result  = {
                        "#      ",
                        "#      ",
                        "#      ",
                        "#      ",
                        "#      ",
                        "#      ",
                        "#######"};
                return result;
            }
            case 'M' : {
                String[] result  = {
                        "#     #",
                        "##   ##",
                        "# # # #",
                        "#  #  #",
                        "#     #",
                        "#     #",
                        "#     #"};
                return result;
            }
            case 'N' : {
                String[] result  = {
                        "#     #",
                        "##    #",
                        "# #   #",
                        "#  #  #",
                        "#   # #",
                        "#    ##",
                        "#     #"};
                return result;
            }
            case 'O' : {
                String[] result  = {
                        "#######",
                        "#     #",
                        "#     #",
                        "#     #",
                        "#     #",
                        "#     #",
                        "#######"};
                return result;
            }
            case 'P' : {
                String[] result  = {
                        "###### ",
                        "#     #",
                        "#     #",
                        "###### ",
                        "#      ",
                        "#      ",
                        "#      "};
                return result;
            }
            case 'Q' : {
                String[] result  = {
                        " ##### ",
                        "#     #",
                        "#     #",
                        "#     #",
                        "#   # #",
                        "#    # ",
                        " #### #"};
                return result;
            }
            case 'R' : {
                String[] result  = {
                        "###### ",
                        "#     #",
                        "#     #",
                        "###### ",
                        "#   #  ",
                        "#    # ",
                        "#     #"};
                return result;
            }
            case 'S' : {
                String[] result  = {
                        " ##### ",
                        "#     #",
                        "#      ",
                        " ##### ",
                        "      #",
                        "#     #",
                        " ##### "};
                return result;
            }
            case 'T' : {
                String[] result  = {
                        "#######",
                        "   #   ",
                        "   #   ",
                        "   #   ",
                        "   #   ",
                        "   #   ",
                        "   #   "};
                return result;
            }
            case 'U' : {
                String[] result  = {
                        "#     #",
                        "#     #",
                        "#     #",
                        "#     #",
                        "#     #",
                        "#     #",
                        " ##### "};
                return result;
            }
            case 'W' : {
                String[] result  = {
                        "#     #",
                        "#  #  #",
                        "#  #  #",
                        "#  #  #",
                        "#  #  #",
                        "#  #  #",
                        " ## ## "};
                return result;
            }
            case 'V' : {
                String[] result  = {
                        "#     #",
                        "#     #",
                        "#     #",
                        "#     #",
                        " #   # ",
                        "  # #  ",
                        "   #   "};
                return result;
            }
            case 'X' : {
                String[] result  = {
                        "#     #",
                        " #   # ",
                        "  # #  ",
                        "   #   ",
                        "  # #  ",
                        " #   # ",
                        "#     #"};
                return result;
            }
            case 'Y' : {
                String[] result  = {
                        "#     #",
                        " #   # ",
                        "  # #  ",
                        "   #   ",
                        "   #   ",
                        "   #   ",
                        "   #   "};
                return result;
            }
            case 'Z' : {
                String[] result  = {
                        "#######",
                        "     # ",
                        "    #  ",
                        "   #   ",
                        "  #    ",
                        " #     ",
                        "#######"};
                return result;
            }
            case ' ' : {
                String[] result  = {
                        "  ",
                        "  ",
                        "  ",
                        "  ",
                        "  ",
                        "  ",
                        "  "};
                return result;
            }
        }

        return null;
    }

}
