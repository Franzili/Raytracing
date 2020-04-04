public class Ray {

    Vec3 A;
    Vec3 B;

    public Ray(Vec3 a, Vec3 b) {
        this.A = a;
        this.B = b;
    }

    Vec3 origin() {
        return this.A;
    }

    Vec3 direction() {
        return this.B;
    }

    Vec3 point_at_Parameter( float t) {
        return this.A.plus(this.B.multiply(t));
    }
}
