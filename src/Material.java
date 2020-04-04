import javafx.util.Pair;

public abstract class Material {

    Ray scattered;
    Vec3 attenuation;
    Vec3 target;

    abstract Pair<Ray, Vec3> scatter(Ray r_in, Hit_Record record);

    public Ray getScattered() {
        return scattered;
    }

    public void setScattered(Ray r_in) {
        this.scattered = r_in;
    }

    public Vec3 getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Vec3 attenuation) {
        this.attenuation = attenuation;
    }

    public Vec3 getTarget() {
        return target;
    }

    public void setTarget(Vec3 target) {
        this.target = target;
    }
}
