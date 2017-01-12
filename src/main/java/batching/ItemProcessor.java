package batching;

import model.Vehicle;
import javax.inject.Named;

/**
 * Created by Dejv on 12.01.17.
 */
@Named
public class ItemProcessor implements javax.batch.api.chunk.ItemProcessor {

    @Override
    public Vehicle processItem(Object t) {
       return (Vehicle) t;
    }

}
