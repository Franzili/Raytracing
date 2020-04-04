import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Raytracing in a Weekend.
 */
public class Renderer {

    public static void main(String[] args) {
        int nx = 2000;
        int ny = 1000;
        int ns = 200;
        int max_depth = 50;

        String firstLine = "P3\n" + nx + " " + ny + "\n255\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstLine);

        Hitable_List world = new Hitable_List();
        Sphere sphere1 = new Sphere(new Vec3(0f, 0f, -1f), 0.5f, new Lambertian(new Vec3(0.7f, 0.3f, 0.3f)));
        world.addHitable(sphere1);
        Sphere sphere2 = new Sphere(new Vec3(0f, -100.5f, -1), 100, new Lambertian(new Vec3(0.8f, 0.8f, 0f)));
        world.addHitable(sphere2);
        Sphere sphere3 = new Sphere(new Vec3(1f, 0f, -1), 0.5f, new Metal(new Vec3(0.8f, 0.6f, 0.2f)));
        world.addHitable(sphere3);
        Sphere sphere4 = new Sphere(new Vec3(-1f, 0.f, -1), 0.5f, new Metal(new Vec3(0.8f, 0.8f, 0.8f)));
        world.addHitable(sphere4);
        Camera camera = new Camera();

        for (int j = ny - 1; j >= 0; j--) {
            for (int i = 0; i < nx; i++) {
                Vec3 col = new Vec3(0f, 0f, 0f);
                for (int s = 0; s < ns; s++) {
                    float u   = ((i + (float) Math.random()) / (float) nx);
                    float v   = ((j + (float) Math.random()) / (float) ny);

                    Ray ray  = camera.getRay(u, v);
                    Vec3 p   = ray.point_at_Parameter(2f);
                    Vec3 c   = color(ray, world, max_depth);
                    col      = c.plus(col);
                }

                col    = col.divideBy((float) ns);
                col    = new Vec3((float) Math.sqrt(col.x()), (float) Math.sqrt(col.y()), (float) Math.sqrt(col.z()));
                int ir = (int) (255.99 * col.x());
                int ig = (int) (255.99 * col.y());
                int ib = (int) (255.99 * col.z());
                stringBuilder.append(ir + " " + ig + " " + ib + "\n");
            }
        }

        try {
            drawImage(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Vec3 color(Ray ray, Hitable hitable, int depth) {
        Hit_Record record = hitable.hit(ray, 0.001f, Float.MAX_VALUE);
        if (depth <= 0) {
            return new Vec3(0, 0, 0);
        }
        if (record != null) {
            Material material = record.getMaterial();
            Pair<Ray, Vec3> data = material.scatter(ray, record);
            if (data != null) {
                Ray scattered = data.getKey();
                Vec3 attenuation = data.getValue();
                return attenuation.multiply(color(scattered, hitable, depth-1));
            }
            return new Vec3(0, 0, 0);
        }

        Vec3 unit_direction = Vec3.unit_vector(ray.direction());
        float t = 0.5f * (unit_direction.y() + 1f);
        return new Vec3(1f, 1f, 1f).multiply(1f - t).plus(new Vec3(0.5f, 0.7f, 1f).multiply(t));
    }

    private static float hit_sphere(Vec3 center, float radius, Ray ray) {
        Vec3 oc = ray.origin().minus(center);
        float a  = Vec3.dot(ray.direction(), ray.direction());
        float b = 2f * Vec3.dot(oc, ray.direction());
        float c = Vec3.dot(oc, oc) - radius * radius;
        float discriminent = b * b - 4 * a * c;
        if (discriminent < 0) {
            return -1;
        }
        return (-b - (float) Math.sqrt(discriminent)) / (2f * a);
    }

    public static Vec3 random_in_unit_sphere() {
        Vec3 p;
        do {
            p = new Vec3((float) Math.random(), (float) Math.random(), (float) Math.random()).multiply(2f).minus(new Vec3(1f, 1f, 1f));
        } while (p.length() * p.length() >= 1f);
        return p;
    }

    private static void drawImage(String image) throws IOException {
        FileWriter fw = new FileWriter("Image.ppm");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(image);
        bw.close();
    }
}
