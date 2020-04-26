package cells.Cells;

import cells.BackedUpCell;
import java.util.Stack;

public class BackedUpMutableCell<T> extends MutableCell<T> implements BackedUpCell<T> {

  private Mode mode;
  private Stack<T> previousValues;
  private int backUpLimit;

  public BackedUpMutableCell(T value) {
    super(value);
    previousValues = new Stack<>();
    mode = Mode.UNBOUNDED;
    backUpLimit = Integer.MAX_VALUE;
  }

  public BackedUpMutableCell() {
    super();
    mode = Mode.UNBOUNDED;
    backUpLimit = Integer.MAX_VALUE;
    previousValues = new Stack<>();
  }

  public BackedUpMutableCell(int backUpLimit) {
    super();
    if (backUpLimit < 0) {
      throw new IllegalArgumentException();
    }
    mode = Mode.BOUNDED;
    this.backUpLimit = backUpLimit;
    previousValues = new Stack<>();
  }

  @Override
  public boolean hasBackup() {
    return !previousValues.isEmpty();
  }

  @Override
  public void revertToPrevious() {
    if (!hasBackup()) {
      throw new UnsupportedOperationException();
    }
    T backUpVal = previousValues.pop();
    super.set(backUpVal);
  }

  @Override
  public void set(T value) {
    if (isSet()) {
      if (previousValues.size() == backUpLimit && mode == Mode.BOUNDED && hasBackup()) {
        previousValues.remove(0);
      }
      if (backUpLimit > 0) {
        previousValues.push(super.get());
      }
    }
    super.set(value);
  }


}
