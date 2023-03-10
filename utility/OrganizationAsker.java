package utility;

import exceptions.*;
import models.Coordinates;
import models.Country;
import models.Person;
import models.TicketType;
import work.Main;

import java.time.LocalDate;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class OrganizationAsker {
    private final Float min_x = (float) -534;
    private final long min_discount = 0;
    private final long max_discount = 100;
    private final int min_price = 0;

    private Scanner userScanner;
    static private boolean fileMode = false;
    Date date = new Date();
    private LocalDate creationDate;

    public OrganizationAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
        creationDate = LocalDate.now();
    }

    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public Scanner getUserScanner() {
        return userScanner;
    }

    public void setFileMode() {
        fileMode = true;
    }

    public void setUserMode() {
        fileMode = false;
    }

    public String askName() throws IncorrectScriptException {
        String name;

        while (true) {
            try {
                Console.println("Введите имя:");
                Console.print(Main.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя не распознано!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    public Coordinates askCoordinates() throws IncorrectScriptException {
        Float x;
        float y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    public float askY() throws IncorrectScriptException {
        String strY;
        float y;
        while (true) {
            try {
                Console.println("Введите координату Y:");
                Console.print(Main.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Float.parseFloat(strY);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата Y не распознана!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }

    public Float askX() throws IncorrectScriptException {
        String strX;
        Float x;
        while (true) {
            try {
                Console.println("Введите координату X > " + min_x + ":");
                Console.print(Main.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Float.parseFloat(strX);
                if (x < min_x) throw new NotDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Координата X не распознана!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NotDeclaredLimitsException exception) {
                Console.printerror("Координата X не может быть меньше " + min_x + "!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }
    public TicketType askTicketType() throws IncorrectScriptException {
        String strTicketType;
        TicketType type;
        while (true) {
            try {
                Console.println("Список категорий билетов- " + TicketType.nameList());
                Console.println("Введите категорию билета:");
                Console.print(Main.PS2);
                strTicketType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strTicketType);
                type = TicketType.valueOf(strTicketType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Тип билета не распознан!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Типа билета нет в списке!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return type;
    }

    public int askPrice() throws IncorrectScriptException {
        String strPrice;
        int price;
        while (true) {
            try {
                Console.println("Введите цену");
                Console.print(Main.PS2);
                strPrice = userScanner.nextLine().trim();
                if (fileMode) Console.println(strPrice);
                price = Integer.parseInt(strPrice);
                if (price < min_price) throw new NotDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Такой цены не может существовать");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Цены считаются цифрами!(желательно арабскими)");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }catch (NotDeclaredLimitsException exception){
                Console.println("Цена должна быть больше нуля");
                if (fileMode) throw new IncorrectScriptException();
            }
        }
        return price;

    }

    public long askDiscount() throws IncorrectScriptException {
        String strDiscount;
        long dicsount;
        while (true) {
            try {
                Console.println("Введите скидку");
                Console.print(Main.PS2);
                strDiscount = userScanner.nextLine().trim();
                if (fileMode) Console.println(strDiscount);
                dicsount = Long.parseLong(strDiscount);
                if (dicsount > max_discount) throw new NotDeclaredLimitsException();
                if (min_discount > dicsount) throw new NotDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Скидку не дали из-за того что просто потому что");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Скидка должна быть представлена числом");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            } catch (NotDeclaredLimitsException exception) {
                Console.println("Скидка должна быть больше нуля и меньше ста, иначе это уже сумасшествие какое-то");
                if (fileMode) throw new IncorrectScriptException();
            }

        }
        return dicsount;

    }
    public Boolean askRefund() throws IncorrectScriptException {
        String strRefund;
        Boolean refund;
        while (true) {
            try {
                Console.println("Введите количество денег на возрат");
                Console.print(Main.PS2);
                strRefund = userScanner.nextLine().trim();
                if (fileMode) Console.println(strRefund);
                refund = Boolean.parseBoolean(strRefund);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Возрат не принимают");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Деньги считаются в числах, подуймайте еще раз над своим вводом");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return refund;
    }

    /** public String askPersonName() throws IncorrectScriptException {
        String PersonName;
        while (true) {
            try {
                Console.println("Введите имя человека:");
                Console.print(Main.PS2);
                PersonName = userScanner.nextLine().trim();
                if (fileMode) Console.println(PersonName);
                if (PersonName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Имя человека не распознано!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя человека не может быть пустым!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return PersonName;
    } */
    public Person askPerson() throws IncorrectScriptException{
        Date birthday;
        Float height;
        Float weight;
        Country nationality;

        birthday = askDate();
        height = askHeight();
        weight = askWeight();
        nationality = askNationality();
        return  new Person(birthday, height, weight, nationality);
    }
    public Float askHeight() throws IncorrectScriptException {
        String strHeight;
        Float height;
        while (true) {
            try {
                Console.println("Введите показатели роста");
                Console.print(Main.PS2);
                strHeight = userScanner.nextLine().trim();
                if (fileMode) Console.println(strHeight);
                height = Float.parseFloat(strHeight);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Рост не распознан");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Рост должен быть представлен числом!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return height;
    }

    public Float askWeight() throws IncorrectScriptException {
        String strWeight;
        Float weight;
        while (true) {
                try {
                    Console.println("Введите показатели веса");
                    Console.print(Main.PS2);
                    strWeight = userScanner.nextLine().trim();
                    if (fileMode) Console.println(strWeight);
                    weight = Float.parseFloat(strWeight);
                    break;
                } catch (NoSuchElementException exception) {
                    Console.printerror("Вес не распознан");
                    if (fileMode) throw new IncorrectScriptException();
                } catch (NumberFormatException exception) {
                    Console.printerror("Вес должен быть представлен числом!");
                    if (fileMode) throw new IncorrectScriptException();
                } catch (NullPointerException | IllegalStateException exception) {
                    Console.printerror("Непредвиденная ошибка!");
                    System.exit(0);
                }
            }
        return weight;
    }

        public Country askNationality () throws IncorrectScriptException {
            Country nationality;
            String strNationality;
            while (true) {
                try {
                    Console.println("Список доступных национальностей человека - " + Country.nameList());
                    Console.println("Введите национальность человека:");
                    Console.print(Main.PS2);
                    strNationality = userScanner.nextLine().trim();
                    if (fileMode) Console.println(strNationality);
                    nationality = Country.valueOf(strNationality.toUpperCase());
                    break;
                } catch (NoSuchElementException exception) {
                    Console.printerror("Национальность не распознана");
                    if (fileMode) throw new IncorrectScriptException();
                } catch (IllegalArgumentException exception) {
                    Console.printerror("Страны нет в списке");
                    if (fileMode) throw new IncorrectScriptException();
                } catch (IllegalStateException exception) {
                    Console.printerror("Непредвиденная ошибка!");
                    System.exit(0);
                }
            }
            return nationality;
        }


    public Date askDate() throws IncorrectScriptException{
      String strDate;
        while(true){
            try{
                Console.println("Введите дату");
                Console.print(Main.PS2);
                strDate = userScanner.nextLine().trim();
                if (fileMode) Console.println(strDate);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Дата не распознана");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Дата должна быть представлена числом!");
                if (fileMode) throw new IncorrectScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return date;
        }


        @Override
        public String toString () {
            return "Organisation Asker is helper class for human's mad fantasy";
        }
    }


