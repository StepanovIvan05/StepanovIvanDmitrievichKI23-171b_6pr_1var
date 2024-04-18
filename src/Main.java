import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main class containing the main method to run the program.
 */
public class Main {
  static Scanner scanner = new Scanner(System.in);

  static CustomArrayCollection<Integer> intCollection;
  static CustomArrayCollection<String> stringCollection;

  /**
   * Main method to run the program.
   */
  public static void main(String[] args) {
    boolean finish = false;

    // Main program loop
    while (!finish) {
      System.out.println("""
              1. Create array
              2. Finish""");
      String choice = scanner.nextLine();
      int size;
      switch (choice) {
        case "1":
          System.out.println("Enter type of data(Integer, String):");
          String type = scanner.nextLine();
          try {
            if (Objects.equals(type, "Integer")) {
              System.out.println("Enter array's size(1-100):");
              size = scanner.nextInt();
              if (size < 1 || size > 100) {
                throw new InputMismatchException();
              }
              scanner.nextLine();
              intCollection = new CustomArrayCollection<>(size);
            } else if (Objects.equals(type, "String")) {
              System.out.println("Enter array's size(1-100):");
              size = scanner.nextInt();
              if (size < 1 || size > 100) {
                throw new InputMismatchException();
              }
              scanner.nextLine();
              stringCollection = new CustomArrayCollection<>(size);
            } else {
              System.out.println("Incorrect type");
              break;
            }
            Menu();
          } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid size");
          }
          break;
        case "2":
          finish = true;
          break;
        default:
          System.out.println("Incorrect choice");
      }
    }
  }

  /**
   * Method to display the menu and handle user choices.
   */
  public static void Menu() {
    boolean exit = false;
    while (!exit) {
      System.out.println("""
              1. Create new array or exit
              2. Read element at index
              3. Change element at index
              4. Change index of begin
              5. Change index of end
              6. Inverting array
              7. Concatenate two arrays
              8. First entry of element
              9. Entry array after index
              10. Display array
              (1-10):""");
      String choice = scanner.nextLine();
      int index;
      int size;
      Integer[] intArray;
      String[] stringArray;
      try {
        switch (choice) {
          case "1":
            intCollection = null;
            stringCollection = null;
            exit = true;
            break;
          case "2":
            System.out.println("Enter index(" +
                    ((stringCollection != null) ? stringCollection.getStartIndex() : intCollection.getStartIndex()) +
                    " - " +
                    ((stringCollection != null) ? stringCollection.getEndIndex() : intCollection.getEndIndex()) + ") ");
            index = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Element at index " + index + " equals " +
                    ((stringCollection != null) ? stringCollection.get(index) : intCollection.get(index)));
            break;
          case "3":
            System.out.println("Enter index(" +
                    ((stringCollection != null) ? stringCollection.getStartIndex() : intCollection.getStartIndex()) +
                    " - " +
                    ((stringCollection != null) ? stringCollection.getEndIndex() : intCollection.getEndIndex()) + ") ");
            index = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter new value:");
            String newStringValue = (stringCollection != null) ? scanner.nextLine() : null;
            int newIntValue = (intCollection != null) ? scanner.nextInt() : 0;
            if (stringCollection != null) {
              stringCollection.set(index, newStringValue);
            } else {
              scanner.nextLine();
              intCollection.set(index, newIntValue);
            }
            System.out.println("Element at index " + index + " changed to " +
                    ((stringCollection != null) ? newStringValue : newIntValue));
            break;
          case "4":
            System.out.println("Enter start index:(-100 - 100)");
            index = scanner.nextInt();
            scanner.nextLine();
            if (index < -100 || index > 100) {
              throw new IndexOutOfBoundsException();
            }
            if (stringCollection != null) {
              stringCollection.changeStartIndex(index);
            } else {
              intCollection.changeStartIndex(index);
            }
            System.out.println("Start index changed to " + index);
            break;
          case "5":
            System.out.println("Enter end index(0 - 200):");
            index = scanner.nextInt();
            if (index < 0 || index > 200) {
              throw new IndexOutOfBoundsException();
            }
            scanner.nextLine();
            if (stringCollection != null) {
              stringCollection.changeEndIndex(index);
            } else {
              intCollection.changeEndIndex(index);
            }
            System.out.println("End index changed to " + index);
            break;
          case "6":
            if (stringCollection != null) {
              stringCollection.invertingArray();
              stringCollection.displayArray(stringCollection.getStartIndex(), stringCollection.getEndIndex());
            } else {
              intCollection.invertingArray();
              intCollection.displayArray(intCollection.getStartIndex(), intCollection.getEndIndex());
            }
            break;
          case "7":
            System.out.println("Enter size of second array to concatenate with first(1-100):");
            size = scanner.nextInt();
            if (size < 1 || size > 100) {
              throw new NegativeArraySizeException();
            }
            scanner.nextLine();
            intArray = new Integer[size];
            stringArray = new String[size];
            System.out.println("Enter second array with enters:");
            for (int i = 0; i < size; i++) {
              if (intCollection != null) {
                intArray[i] = scanner.nextInt();
                scanner.nextLine();
              } else {
                stringArray[i] = scanner.nextLine();
              }
            }
            if (stringCollection != null) {
              stringCollection.concatenateArrays(stringArray, size);
              stringCollection.displayArray(stringCollection.getStartIndex(), stringCollection.getEndIndex());
            } else {
              intCollection.concatenateArrays(intArray, size);
              intCollection.displayArray(intCollection.getStartIndex(), intCollection.getEndIndex());
            }
            break;
          case "8":
            System.out.println("Enter value to get first entry");
            if (stringCollection != null) {
              String value = scanner.nextLine();
              index = stringCollection.firstEntry(value);
            } else {
              int value = scanner.nextInt();
              index = intCollection.firstEntry(value);
              scanner.nextLine();
            }
            System.out.println(((index == -10001) ? "Element is not in array" : "First entry of element has index: "
                    + index));
            break;
          case "9":
            System.out.println("Enter size of second array to entry(1 - 100):");
            size = scanner.nextInt();
            if (size < 1 || size > 100) {
              throw new NegativeArraySizeException();
            }
            scanner.nextLine();
            intArray = new Integer[size];
            stringArray = new String[size];
            System.out.println("Enter second array with enters:");
            for (int i = 0; i < size; i++) {
              if (intCollection != null) {
                intArray[i] = scanner.nextInt();
                scanner.nextLine();
              } else {
                stringArray[i] = scanner.nextLine();
              }
            }
            System.out.println("Enter index to entry array(" +
                    ((stringCollection != null) ? stringCollection.getStartIndex() - 1 :
                            intCollection.getStartIndex() - 1) + " - " +
                    ((stringCollection != null) ? stringCollection.getEndIndex() : intCollection.getEndIndex()) + ") ");
            index = scanner.nextInt();
            scanner.nextLine();
            if (stringCollection != null) {
              stringCollection.enterArrayAfterIndex(stringArray, size, index);
              stringCollection.displayArray(stringCollection.getStartIndex(), stringCollection.getEndIndex());
            } else {
              intCollection.enterArrayAfterIndex(intArray, size, index);
              intCollection.displayArray(intCollection.getStartIndex(), intCollection.getEndIndex());
            }
            break;
          case "10":
            System.out.println("Enter start and end indexes to display(" +
                    ((stringCollection != null) ? stringCollection.getStartIndex() : intCollection.getStartIndex()) +
                    " - " +
                    ((stringCollection != null) ? stringCollection.getEndIndex() : intCollection.getEndIndex()) + ") ");
            System.out.println("Start index: ");
            int startIndex = scanner.nextInt();
            scanner.nextLine();
            System.out.println("End index: ");
            int endIndex = scanner.nextInt();
            scanner.nextLine();
            if (stringCollection != null) {
              stringCollection.displayArray(startIndex, endIndex);
            } else {
              intCollection.displayArray(startIndex, endIndex);
            }
          default:
            System.out.println("Invalid choice");
        }
      } catch (InputMismatchException e) {
        scanner.nextLine();
        System.out.println("Input number error");
      } catch (IndexOutOfBoundsException e) {
        System.out.println("Incorrect index");
      } catch (NegativeArraySizeException e) {
        scanner.nextLine();
        System.out.println("Incorrect size");
      }
    }
  }
}
