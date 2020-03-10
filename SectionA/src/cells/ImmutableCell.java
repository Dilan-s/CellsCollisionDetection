package cells;

import java.util.Objects;

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
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImmutableCell)) {
      return false;
    }
    ImmutableCell<?> that = (ImmutableCell<?>) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
