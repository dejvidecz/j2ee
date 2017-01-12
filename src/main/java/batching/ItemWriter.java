package batching;

import model.Vehicle;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Dejv on 12.01.17.
 */
@Named
public class ItemWriter extends AbstractItemWriter {


    @Override
    public void writeItems(List list) {
        System.out.println("writeItems: " + list);
        for (Object newhire : list) {
            System.out.println("writing"+((Vehicle) newhire).getColor());
        }
    }

}
