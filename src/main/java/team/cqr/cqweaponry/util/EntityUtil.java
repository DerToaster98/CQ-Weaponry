package team.cqr.cqweaponry.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShapes;

public class EntityUtil {

	public static void move2D(Entity entity, double strafe, double forward, double speed, double yaw) {
		double d = strafe * strafe + forward * forward;
		if (d >= 1.0E-4D) {
			d = Math.sqrt(d);
			if (d < 1.0D) {
				d = 1.0D;
			}
			d = speed / d;

			strafe *= d;
			forward *= d;

			double d1 = Math.sin(Math.toRadians(yaw));
			double d2 = Math.cos(Math.toRadians(yaw));

			entity.setDeltaMovement(entity.getDeltaMovement().add(
					strafe * d2 - forward * d1,
					0,
					forward * d2 + strafe * d1
			));
			
			/*entity.motionX += strafe * d2 - forward * d1;
			entity.motionZ += forward * d2 + strafe * d1;*/
		}
	}

	public static void move3D(Entity entity, double strafe, double up, double forward, double speed, double yaw, double pitch) {
		double d = strafe * strafe + up * up + forward * forward;
		if (d >= 1.0E-4D) {
			d = Math.sqrt(d);
			if (d < 1.0D) {
				d = 1.0D;
			}
			d = speed / d;

			strafe *= d;
			up *= d;
			forward *= d;

			double d1 = Math.sin(Math.toRadians(yaw));
			double d2 = Math.cos(Math.toRadians(yaw));
			double d3 = Math.sin(Math.toRadians(pitch));
			double d4 = Math.cos(Math.toRadians(pitch));

			entity.setDeltaMovement(entity.getDeltaMovement().add(
					strafe * d2 - forward * d1 * d4,
					up - forward * d3,
					forward * d2 * d4 + strafe * d1
			));
			/*entity.motionX += strafe * d2 - forward * d1 * d4;
			entity.motionY += up - forward * d3;
			entity.motionZ += forward * d2 * d4 + strafe * d1;*/
		}
	}

	public static boolean isEntityFlying(Entity entity) {
		if (entity.isOnGround()) {
			return false;
		}
		if (entity.horizontalCollision || entity.verticalCollision) {
			return false;
		}
		if (entity.getDeltaMovement().y() < -0.1D) {
			return false;
		}
		BlockPos pos = entity.blockPosition();
		int y = 0;
		int count = 0;
		BlockPos.Mutable mutablePos = new BlockPos.Mutable();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				mutablePos.set(pos.getX() + i, pos.getY(), pos.getZ() + j);
				if (!entity.level.isLoaded(mutablePos)) {
					continue;
				}
				//TODO: Check if this is correct
				while (mutablePos.getY() > 0 && entity.level.getBlockState(mutablePos).getCollisionShape(entity.level, mutablePos) == VoxelShapes.INFINITY) {
					mutablePos.setY(mutablePos.getY() - 1);
				}
				y += mutablePos.getY() + 1;
				count++;
			}
		}
		y = count > 0 ? y / count : (int) entity.getY();
		if (entity.getY() < y + 8) {
			return false;
		}
		return entity.level.noCollision(entity.getBoundingBox());
	}

}
