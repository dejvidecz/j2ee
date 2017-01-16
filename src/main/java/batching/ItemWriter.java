package batching;

import model.CarOffer;
import model.VehicleOffer;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Dejv on 12.01.17.
 */
@Named
public class ItemWriter extends AbstractItemWriter {


    @Override
    public void writeItems(List list) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(getPathName()));
        StringBuilder sb = new StringBuilder();
        sb.append("title;date_added;");
        sb.append(System.lineSeparator());


        System.out.println("done!");
        for(int i =0; i<list.size();i++){
            CarOffer carOffer = (CarOffer) list.get(i);
            sb.append(carOffer.getTitle());
            sb.append(";");
            sb.append(carOffer.getDate_added().toString());
            sb.append(";");
            sb.append(System.lineSeparator());
        }

        pw.write(sb.toString());
        pw.close();

    }

    private String getPathName(){
        return "test.csv";
    }

}
