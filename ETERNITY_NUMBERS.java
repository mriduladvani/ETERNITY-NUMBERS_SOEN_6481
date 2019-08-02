import java.util.*;
public class ETERNITY_NUMBERS {

    public static void main(String[] args) {

        display_function();
    }

    public static void display_function()
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("**********-Welcome to ETERNITY: NUMBERS-**********");
        System.out.println("**********-Select one of the two applications of the constant:");
        System.out.println("**********-1. Computer Graphics- Image Rendering (User Story 2 & 3)");
        System.out.println("**********-2. Cryptography- Finding cipher text-Salting (User Story 4 & 5) [Problem 8 implementation]");
        System.out.println("**********-Enter option");
        int option = Integer.parseInt(sc.nextLine());
        if(option==1) {
            System.out.println("Enter the base in which you require the Champernowne constant [Integers only](default:10) ");
            int base = Integer.parseInt(sc.nextLine());
            System.out.println("Enter the number of bits of the constant required [Integers only]") ;
            int bits = Integer.parseInt(sc.nextLine());
            System.out.println("Champernowne constant: C-" + base + ": " + graphics(base, bits));
            System.out.println("The constant above for these bits would go through color assignment function");
            System.out.println("Color assignment would result in image rendering.");
        }
        else if (option==2){
            System.out.println("Enter the base in which you require the Champernowne constant [Integers only](default:10) ");
            int base = Integer.parseInt(sc.nextLine());
            System.out.println("Enter the plain text that you need to protect! [Enter anything!]");
            String plain_text = sc.nextLine();
            System.out.println("Plain Text: "+plain_text);
            plain_text = plain_text.replaceAll("\\s", "0");
            double champernowne_value_in_base_10, base_changed_double, champernowne_value_in_req_base;
            String base_changed_string;
            //System.out.println(plain_text_to_binary(plain_text));
            System.out.println("Champernowne constant: C-" + base + ": " + graphics(base, plain_text.length()));
            System.out.println();
            System.out.println("Cipher text: "+calculate_cipher_text(plain_text_to_binary(plain_text)));
            System.out.println("The above cipher text now goes through a hashing function before being stored in " +
                    "actual databases.");
        }
        else
        {
            System.out.println("Incorrect format of option entered.");
        }
        System.out.println("Do you want to go again ? (Y/N)");
        char continue_option= sc.next().charAt(0);
        if(continue_option=='Y' || continue_option=='y')
        {
            display_function();
        }
        else
        {
            return;
        }

    }

    public static double graphics(int base, int bits) {
        double champernowne_value_in_base_10, base_changed_double, champernowne_value_in_req_base;
        String base_changed_string;
        champernowne_value_in_base_10 = calculate_champernowne_constant(base, bits);
        if (base == 10) {
            return champernowne_value_in_base_10;
        } else {
            base_changed_string = base_converter(base, champernowne_value_in_base_10, bits);
            base_changed_double = Double.parseDouble(base_changed_string);
            champernowne_value_in_req_base = base_changed_double / calculate_power(10, base_changed_string.length());
            return champernowne_value_in_req_base;
        }
    }

    public static double calculate_power(double base, double power) {
        double product = 1;
        for (int i = 0; i < power; i++) {
            product = product * base;
        }
        return product;

    }

    public static double calculate_champernowne_constant(int base, int limit) {
        double champernowne_constant = 0;
        int floor_log = 0;

        for (int i = 1; i <= limit; i++) {
            for (int j = 1; j <= i; j++) {
                floor_log = floor_log + (int) calculate_log(j, base);
            }

            champernowne_constant = champernowne_constant + i / (calculate_power(base, (i + floor_log)));
            floor_log = 0;
        }


        return champernowne_constant;
    }


    public static double calculate_log(double x, int base) {

        int decimalplace = 5;
        int integer_value = 0;

        while (x < 1) {
            integer_value--;
            x = x * base;
        }

        while (x >= base) {
            integer_value++;
            x = x / base;
        }
        double decimal_fraction = 0.0;
        double partial = 1.0;

        x = calculate_power(x, 10);

        while (decimalplace > 0) {

            partial = partial / 10;
            int digit = 0;

            while (x >= base) {
                digit++;
                x = x / base;
            }
            decimal_fraction = decimal_fraction + digit * partial;

            x = calculate_power(x, 10);
            decimalplace--;
        }
        return integer_value + decimal_fraction;
    }


    public static char reVal(int num) {
        if (num >= 0 && num <= 9) {
            return (char) (num + 48);
        } else {
            return (char) (num - 10 + 65);
        }

    }

    public static String base_converter(int required_base, double num, int bits) {
        double initial_num = num;
        String remainder = "";
        Double absolute_zero = 0.0;
        int bit_counter = 0;
        ArrayList<Double> arraylist = new ArrayList<>();
        while (Double.compare(num, absolute_zero) != 0) {
            remainder += reVal((int) (num * required_base));
            num = (num * required_base) - (int) (num * required_base);
            //num= (num<=0.0F) ? 0.0F-num: num;
            bit_counter++;
            if (Double.compare(num, initial_num) == 0 || arraylist.contains(num) == true || bit_counter == bits) {
                break;
            }
            arraylist.add(num);

        }
        return remainder;

    }

    public static ArrayList<String> plain_text_to_binary(String plain_text) {
        String binary_plain_text = "";
        ArrayList<String> splitted_binary_plain_text = new ArrayList<String>();

        for (int i = 0; i < plain_text.length(); i++) {
            binary_plain_text += Integer.toBinaryString(Character.valueOf(plain_text.charAt(i)));
        }

        for (int i = 0; i < binary_plain_text.length() - 6; i++) {
            splitted_binary_plain_text.add(binary_plain_text.substring(i, i + 6));
        }

        return splitted_binary_plain_text;
    }

    public static String calculate_cipher_text(ArrayList<String> binary_plain_text)
    {
        String cipher_text="";

        double champernowne_constant= graphics(2,6);
        double multiplier= champernowne_constant*calculate_power(10,6);
        ArrayList<String> al2= new ArrayList<String>();
        for(int i=0;i<binary_plain_text.size();i++)
        {
            for(int j=0;j<binary_plain_text.get(i).length();j++)
            {
                cipher_text+=Character.getNumericValue(binary_plain_text.get(i).charAt(j))^

                        Character.getNumericValue(Double.toString(multiplier).charAt(j))
                        ;
            }

        }
        //Character.valueOf(binary_plain_text.get(i).charAt(j))^
        //                        Character.valueOf(Double.toString(multiplier).charAt(j))
        int min=8;
        for(int i=0;i<cipher_text.length();i++)
        {
            al2.add(cipher_text.substring(i, i+(min<cipher_text.substring(i,cipher_text.length()).length()
            ? min :cipher_text.substring(i,cipher_text.length()).length() )));
            i=i+7;
        }

        String s = "";
        for(int index = 0; index < al2.size(); index++) {
            String temp = al2.get(index);
            int num = Integer.parseInt(temp,2);
            char letter = (char) num;
            s = s+letter;
        }
        //System.out.println(s);
        cipher_text=s;
        return cipher_text;

    }

}
