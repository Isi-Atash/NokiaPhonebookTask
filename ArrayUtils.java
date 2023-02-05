import java.util.Arrays;

public class ArrayUtils {


    //this method is used to increase the size of an array by creating a new array with the new size
    //and copying the old array into the new one
    public static <T> T[] increaseArraySize(T[] arr, int newSize) {
        return Arrays.copyOf(arr, newSize);
    }

    
    public static <T> T[] deleteArrayElement(T[] array, int indexToRemove) {
        if (indexToRemove >= array.length || indexToRemove < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T[] newArray = (T[]) new Object[array.length - 1];
        for (int i = 0, k = 0; i < array.length; i++) {
            if (i == indexToRemove) {
                continue;
            }
            newArray[k++] = array[i];
        }
        return newArray;
    }
}
