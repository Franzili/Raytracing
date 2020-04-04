public class Camera {

    Vec3 lower_left_corner;
    Vec3 horizontal;
    Vec3 vertical;
    Vec3 origin;

    public Camera() {
        lower_left_corner = new Vec3(-2f, -1f, -1f);
        horizontal = new Vec3(4f, 0f, 0f);
        vertical = new Vec3(0f, 2f, 0f);
        origin = new Vec3(0f, 0f, 0f);
    }

    public Ray getRay(float u, float v) {
        return new Ray(origin, lower_left_corner.plus(horizontal.multiply(u)).plus(vertical.multiply(v)).minus(origin));
    }
}
