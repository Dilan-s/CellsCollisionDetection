package cells;

import java.util.Comparator;
import java.util.Stack;

public class BackedUpCellComparator<U> implements Comparator<BackedUpCell<U>> {


  Comparator<U> valueComparator;

  public BackedUpCellComparator(Comparator<U> valueComparator) {
    this.valueComparator = valueComparator;
  }

  @Override
  public int compare(BackedUpCell<U> a, BackedUpCell<U> b) {
    int retVal = 0;
    if (a.isSet() && !b.isSet()) {
      return 1;
    }
    if (!a.isSet() && b.isSet()) {
      return -1;
    }
    if (!a.isSet() && !b.isSet()) {
      return 0;
    }
    int valueComparison = valueComparator.compare(a.get(), b.get());
    if (valueComparison != 0) {
      return valueComparison;
    }
    Stack<U> aStack = new Stack<>();
    Stack<U> bStack = new Stack<>();
    while (a.hasBackup() && b.hasBackup()) {
      aStack.push(a.get());
      a.revertToPrevious();
      bStack.push(b.get());
      b.revertToPrevious();
      valueComparison = valueComparator.compare(a.get(), b.get());
      if (valueComparison != 0) {
        break;
      }
    }
    if (a.hasBackup()) {
      retVal = 1;
    }
    if (b.hasBackup()) {
      retVal = -1;
    }
    rebuild(a, b, aStack, bStack);
    return retVal;
  }

  private void rebuild(BackedUpCell<U> a, BackedUpCell<U> b, Stack<U> aStack, Stack<U> bStack) {
    while (!aStack.isEmpty()) {
      a.set(aStack.pop());
      b.set(bStack.pop());
    }
  }
}
