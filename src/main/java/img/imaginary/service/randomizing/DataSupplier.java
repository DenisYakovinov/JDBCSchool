package img.imaginary.service.randomizing;

import java.util.Set;

public interface DataSupplier<T> {

    Set <T> supplyData();
}
