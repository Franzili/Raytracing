public class Sphere extends Hitable {

    private Hit_Record record;
    private Vec3 center;
    private float radius;
    private Material material;

    public Sphere(Vec3 center, float radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    @Override
    Hit_Record hit(Ray ray, float t_min, float t_max) {
        Vec3 oc = ray.origin().minus(center);
        float a  = Vec3.dot(ray.direction(), ray.direction());
        float b  = Vec3.dot(oc, ray.direction());
        float c  = Vec3.dot(oc, oc) - radius * radius;
        float discriminant = b*b - a*c;
        if (discriminant > 0) {
            float temp = (-b - (float) Math.sqrt(discriminant)) / a;
            if (temp < t_max && temp > t_min) {
                Hit_Record record = new Hit_Record();
                record.setT(temp);
                record.setP(ray.point_at_Parameter(record.getT()));
                record.setNormal((record.getP().minus(center)).divideBy(radius));
                record.setMaterial(this.material);
                return record;
            }
            temp = (-b + (float) Math.sqrt(discriminant)) / a;
            if (temp < t_max && temp > t_min) {
                Hit_Record record = new Hit_Record();
                record.setT(temp);
                record.setP(ray.point_at_Parameter(record.getT()));
                record.setNormal((record.getP().minus(center).divideBy(radius)));
                record.setMaterial(this.material);
                return record;
            }
        }
        return null;
    }

    public Hit_Record getRecord() {
        return record;
    }
}
