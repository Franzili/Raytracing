/**
 * Objects that can get hit by light.
 */
public abstract class Hitable {

    abstract Hit_Record hit(Ray ray, float t_min, float t_max);
}
