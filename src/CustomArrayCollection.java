import java.util.InputMismatchException;
import java.util.Objects;

/**
 * CustomArrayCollection represents a custom array collection that stores elements of type T.
 *
 * @param <T> the type of elements stored in the collection
 */
public class CustomArrayCollection<T> {
  private T[] array;
  private int size;
  private int startIndex;
  private int endIndex;


  /**
   * Constructs a new CustomArrayCollection with the specified size.
   *
   * @param size the initial size of the collection
   */
  public CustomArrayCollection(int size) {
    if (size < 1 || size > 100) {
      throw new InputMismatchException();
    }
    this.array = (T[]) (new Object[size]);
    this.size = size;
    this.startIndex = 0;
    this.endIndex = size - 1;
  }

  // Method to get element at given index
  public T get(int index) {
    if (index < startIndex || index > endIndex) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    return array[index - startIndex];
  }

  // Method to set element at given index
  public void set(int index, T value) {
    if (index < startIndex || index > endIndex) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    array[index - startIndex] = value;
  }

  // Method to change start index
  public void changeStartIndex(int startIndex) {
    if (startIndex < -100 || startIndex > 100) {
      throw new IndexOutOfBoundsException();
    }
    this.startIndex = startIndex;
    this.endIndex = startIndex + size - 1;
  }

  // Method to change end index
  public void changeEndIndex(int endIndex) {
    if (endIndex < 0 || endIndex > 200) {
      throw new IndexOutOfBoundsException();
    }
    this.endIndex = endIndex;
    this.startIndex = endIndex - size + 1;
  }

  // Method to invert the array
  public void invertingArray() {
    T swapper;
    for (int i = 0; i < size / 2; i++) {
      swapper = array[i];
      array[i] = array[size - i - 1];
      array[size - i - 1] = swapper;
    }
  }

  // Method to concatenate arrays
  public void concatenateArrays(T[] secondArray, int secondSize) {
    T[] helpArray = (T[]) (new Object[size]);
    System.arraycopy(array, 0, helpArray, 0, size);
    array = (T[]) (new Object[size + secondSize]);
    System.arraycopy(helpArray, 0, array, 0, size);
    System.arraycopy(secondArray, 0, array, size, secondSize);
    size = array.length;
    endIndex = startIndex + size - 1;
  }

  // Method to get index of first entry of a value
  public int firstEntry(T value) {
    for (int i = 0; i < size; i++) {
      if (Objects.equals(array[i], value)) {
        return i + startIndex;
      }
    }
    return -10001;
  }

  // Method to enter array after given index
  public void enterArrayAfterIndex(T[] secondArray, int secondSize, int index) {
    if (index < startIndex - 1 || index > endIndex) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    index -= startIndex;
    T[] helpArray = (T[]) (new Object[size]);
    System.arraycopy(array, 0, helpArray, 0, size);
    array = (T[]) (new Object[size + secondSize]);
    System.arraycopy(helpArray, 0, array, 0, index + 1);
    System.arraycopy(secondArray, 0, array, index + 1, secondSize);
    System.arraycopy(helpArray, index + 1, array, index + secondSize + 1, size - index - 1);
    size = array.length;
    endIndex = size - 1 + startIndex;
  }

  // Method to display array elements within given range
  public void displayArray(int startDisplayIndex, int endDisplayIndex) {
    if (startDisplayIndex < startIndex || startDisplayIndex > endIndex ||
            endDisplayIndex < startIndex || endDisplayIndex > endIndex) {
      throw new IndexOutOfBoundsException("Index out of bounds");
    }
    for (int i = startDisplayIndex - startIndex; i <= endDisplayIndex - startIndex; i++) {
      System.out.println(array[i]);
    }
  }

  // Getter method for start index
  public int getStartIndex() {
    return startIndex;
  }

  // Getter method for end index
  public int getEndIndex() {
    return endIndex;
  }
}
