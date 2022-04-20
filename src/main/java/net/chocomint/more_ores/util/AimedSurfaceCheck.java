package net.chocomint.more_ores.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static net.minecraft.util.math.Direction.*;

public class AimedSurfaceCheck {

	public static Direction check(PlayerEntity player, BlockPos pos) {
		Vec3d eye = player.getEyePos(), unit = Vec3d.fromPolar(player.getPitch(), player.getYaw());

		List<Pair<Intersection, Direction>> l = Arrays.asList(
				new Pair<>(isInSurface(eye, unit, Axis.X, pos.getX(), pos.getY(), pos.getZ()), WEST),
				new Pair<>(isInSurface(eye, unit, Axis.X, pos.getX() + 1, pos.getY(), pos.getZ()), EAST),
				new Pair<>(isInSurface(eye, unit, Axis.Y, pos.getY(), pos.getX(), pos.getZ()), DOWN),
				new Pair<>(isInSurface(eye, unit, Axis.Y, pos.getY() + 1, pos.getX(), pos.getZ()), UP),
				new Pair<>(isInSurface(eye, unit, Axis.Z, pos.getZ(), pos.getX(), pos.getY()), NORTH),
				new Pair<>(isInSurface(eye, unit, Axis.Z, pos.getZ() + 1, pos.getX(), pos.getY()), SOUTH)
		);
		List<Pair<Intersection, Direction>> res = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			if (l.get(i).getLeft().intersect) {
				res.add(l.get(i));
			}
		}
		res.sort(new Sort());
		return res.get(0).getRight();
	}

	// Line-Plane
	public static Vec3d getIntersection(Vec3d playerEye, Vec3d unit, Axis plane, double value) {
		switch (plane) {
			case X -> {
				double rx = value - playerEye.getX();
				return new Vec3d(rx, rx * unit.y / unit.x, rx * unit.z / unit.x).add(playerEye);
			}
			case Y -> {
				double ry = value - playerEye.getY();
				return new Vec3d(ry * unit.x / unit.y, ry, ry * unit.z / unit.y).add(playerEye);
			}
			case Z -> {
				double rz = value - playerEye.getZ();
				return new Vec3d(rz * unit.x / unit.z, rz * unit.y / unit.z, rz).add(playerEye);
			}
		}
		return playerEye;
	}

	public static Intersection isInSurface(Vec3d playerEye, Vec3d unit, Axis plane, double value, int start1, int start2) {
		Vec3d v = getIntersection(playerEye, unit, plane, value);
		double d = playerEye.distanceTo(v);
		return switch (plane) {
			case X -> new Intersection(inRange(v.y, start1, start1 + 1) && inRange(v.z, start2, start2 + 1), d);
			case Y -> new Intersection(inRange(v.x, start1, start1 + 1) && inRange(v.z, start2, start2 + 1), d);
			case Z -> new Intersection(inRange(v.x, start1, start1 + 1) && inRange(v.y, start2, start2 + 1), d);
		};
	}

	private record Intersection(boolean intersect, double distance) {}

	private static class Sort implements Comparator<Pair<Intersection, Direction>>
	{
		@Override
		public int compare(Pair<Intersection, Direction> o1, Pair<Intersection, Direction> o2) {
			return (int) ((o1.getLeft().distance - o2.getLeft().distance) * 100);
		}
	}

	public static boolean inRange(double val, double min, double max) {
		return val >= min && val <= max;
	}
}
