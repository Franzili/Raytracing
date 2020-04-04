import java.util.ArrayList;
import java.util.Collection;

public class Hitable_List extends Hitable {

    private static ArrayList<Hitable> list = new ArrayList<Hitable>();

    public Hitable_List() {}

    public Hitable_List(ArrayList<Hitable> list) {
        this.list = list;
    }

    @Override
    Hit_Record hit(Ray ray, float t_min, float t_max) {
        Hit_Record temp_rec = null;
        float closest_so_far = t_max;
        for (Hitable hitable : list) {
            Hit_Record hit_record = hitable.hit(ray, t_min, closest_so_far);
            if (hit_record != null) {
                closest_so_far = hit_record.getT();
                temp_rec = hit_record;
            }
        }
        return temp_rec;
    }

    public void addHitable(Hitable hitable) {
        list.add(hitable);
    }
}
