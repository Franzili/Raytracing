import javafx.util.Pair;

import java.beans.VetoableChangeListener;

public class Metal extends Material {

    Vec3 albedo;

    public Metal(Vec3 albedo) {
        this.albedo = albedo;
    }

    @Override
    Pair<Ray, Vec3> scatter(Ray r_in, Hit_Record record) {
        Vec3 reflected = Vec3.reflect(Vec3.unit_vector(r_in.direction()), record.getNormal());
        Ray scattered = new Ray(record.getP(), reflected);
        Vec3 attenuation = albedo;
        if (Vec3.dot(scattered.direction(), record.getNormal()) > 0) {
            return new Pair<>(scattered, attenuation);
        }
        return null;
    }
}
