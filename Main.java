import java.util.*;
public class Main {

    public static void main(String[] args)
    {
       Scanner sc= new Scanner(System.in);
        /*System.out.println("Welcome to ETERNITY: NUMBERS");
        System.out.println("Select one of the two applications of the constant:");
        System.out.println("1. Computer Graphics (User Story 2 & 3)");
        System.out.println("2. Cryptography (User Story 4 & 5)");*/
        System.out.println("Enter the base in which you require the Champernowne constant (default:10) ");
        int base= sc.nextInt();
        System.out.println("Enter the number of bits of the constant required");
        int bits= sc.nextInt();
        System.out.println("Enter your plain text");
        String plain_text= sc.nextLine();
        double champernowne_value_in_base_10, base_changed_double, champernowne_value_in_req_base;
        String base_changed_string;


        champernowne_value_in_base_10=calculate_champernowne_constant(base, bits);
        if(base==10)
        {
            System.out.println("Champernowne constant: "+champernowne_value_in_base_10);
        }
        else
        {
            base_changed_string=base_converter(base, champernowne_value_in_base_10, bits);
            base_changed_double= Double.parseDouble(base_changed_string);
            champernowne_value_in_req_base= base_changed_double/calculate_power(10, base_changed_string.length());
            System.out.println("Champernowne constant: "+champernowne_value_in_req_base);
        }


    }

    public static double calculate_power(double base, double power)
    {
        double product=1;
        for(int i=0;i<power;i++)
        {
            product=product*base;
        }
        return product;

    }

    public static double calculate_champernowne_constant(int base, int limit)
    {
        double champernowne_constant=0;
        int floor_log=0;

        for(int i=1;i<=limit;i++)
        {
            for(int j=1;j<=i;j++)
            {
                floor_log= floor_log + (int)calculate_log(j, base);
            }

            champernowne_constant= champernowne_constant + i/(calculate_power(base, (i+floor_log)));
            floor_log=0;
        }



        return champernowne_constant;
    }


    public static double calculate_log(double x, int base) {

        int decimalplace=5;
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
        }
        else
        {
            return (char) (num-10+65);
        }

    }

    public static String base_converter(int required_base, double num, int bits)
    {
        double initial_num=num;
        String remainder="";
        Double absolute_zero=0.0;
        int bit_counter=0;
        ArrayList<Double> arraylist= new ArrayList<>();
        while(Double.compare(num, absolute_zero)!=0 )
        {
            remainder+=reVal((int)(num*required_base));
            num=(num*required_base)- (int)(num*required_base);
            //num= (num<=0.0F) ? 0.0F-num: num;
            bit_counter++;
            if(Double.compare(num, initial_num)==0 || arraylist.contains(num)==true || bit_counter==bits) {break;}
            arraylist.add(num);

        }
        return remainder;

    }
}
