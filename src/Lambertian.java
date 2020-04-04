import javafx.util.Pair;

public class Lambertian extends Material {

    Vec3 albedo;

    public Lambertian(Vec3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public Pair<Ray, Vec3> scatter(Ray r_in, Hit_Record record) {
        Vec3 target = record.getP().plus(record.getNormal().plus(Renderer.random_in_unit_sphere()));
        Ray scattered = new Ray(record.getP(), target.minus(record.getP()));
        Vec3 attenuation = albedo;
        return new Pair<>(scattered, attenuation);
    }
}
