package cells.Cells;

import cells.Cell;

public class ImmutableCell<T> implements Cell<T> {

  private final T value;

  public ImmutableCell(T value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    this.value = value;
  }

  @Override
  public void set(T value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public boolean isSet() {
    return value != null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImmutableCell)) {
      return false;
    }
    ImmutableCell<T> that = (ImmutableCell<T>) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}
