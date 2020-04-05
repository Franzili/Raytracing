public class Vec3 {

    public float e[] = new float[3];

    Vec3(float e0, float e1, float e2) {
        this.e[0] = e0;
        this.e[1] = e1;
        this.e[2] = e2;
    }

    public float x() {
        return e[0];
    }

    public float y() {
        return e[1];
    }

    public float z() {
        return e[2];
    }

    public float r() {
        return e[0];
    }

    public float g() {
        return e[1];
    }

    public float b() {
        return e[2];
    }

    public Vec3 negate() {
        e[0] = - this.e[0];
        e[1] = - this.e[1];
        e[2] = - this.e[2];
        return new Vec3(e[0], e[1], e[2]);
    }

    public Vec3 plus(Vec3 vector) {
        float ix = this.e[0] + vector.x();
        float iy = this.e[1] + vector.y();
        float iz = this.e[2] + vector.z();
        return new Vec3(ix, iy, iz);
    }

    public Vec3 plus(float num) {
        float ix = this.e[0] + num;
        float iy = this.e[1] + num;
        float iz = this.e[2] + num;
        return new Vec3(ix, iy, iz);
    }

    public Vec3 minus(Vec3 vector) {
        float ix = this.e[0] - vector.x();
        float iy = this.e[1] - vector.y();
        float iz = this.e[2] - vector.z();
        return new Vec3(ix, iy, iz);
    }

    public Vec3 minus(float number) {
        float ix = this.e[0] - number;
        float iy = this.e[1] - number;
        float iz = this.e[2] - number;
        return new Vec3(ix, iy, iz);
    }

    public float dotMultiplication(float t) {
        float ix = this.e[0] * t;
        float iy = this.e[1] * t;
        float iz = this.e[2] * t;
        return ix + iy + iz;
    }

    public Vec3 multiply(float t) {
        float ix = this.e[0] * t;
        float iy = this.e[1] * t;
        float iz = this.e[2] * t;
        return new Vec3(ix, iy, iz);
    }

    public Vec3 multiply(Vec3 vector) {
        float ix = this.e[0] * vector.x();
        float iy = this.e[1] * vector.y();
        float iz = this.e[2] * vector.z();
        return new Vec3(ix, iy, iz);
    }

    public Vec3 divideBy(float t) {
        float ix = this.e[0] / t;
        float iy = this.e[1] / t;
        float iz = this.e[2] / t;
        return new Vec3(ix, iy, iz);
    }

    public static Vec3 reflect(Vec3 v, Vec3 n) {
        return v.minus(n.multiply(dot(v, n) * 2));
    }

    public Vec3 refract(Vec3 uv, Vec3 n, float etai_over_etat) {
        float cos_theta = dot(uv.negate(), n);
        Vec3 r_out_parallel = (uv.plus(cos_theta)).multiply(etai_over_etat);
        Vec3 r_out_perp = n.multiply(- (float) Math.sqrt(1f - (float) Math.pow(r_out_parallel.length(), 2)));
        return r_out_parallel.plus(r_out_perp);
    }

    public static float dot(Vec3 vector1, Vec3 vector2) {
        return vector1.e[0] * vector2.e[0] + vector1.e[1] * vector2.e[1] + vector1.e[2] * vector2.e[2];
    }

    public float length() {
        return (float) Math.sqrt(this.e[0] * this.e[0] + this.e[1] * this.e[1] + this.e[2] * this.e[2]);
    }

    public static Vec3 unit_vector(Vec3 vector) {
        return vector.divideBy(vector.length());
    }
}
