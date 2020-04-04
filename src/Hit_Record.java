public class Hit_Record {

    private float t;
    private Vec3 p;
    private Vec3 normal;
    private Material material;

    public Hit_Record() {
    }

    public float getT() {
        return t;
    }

    public Vec3 getP() {
        return p;
    }

    public Vec3 getNormal() {
        return normal;
    }

    public void setT(float t) {
        this.t = t;
    }

    public void setP(Vec3 p) {
        this.p = p;
    }

    public void setNormal(Vec3 normal) {
        this.normal = normal;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
