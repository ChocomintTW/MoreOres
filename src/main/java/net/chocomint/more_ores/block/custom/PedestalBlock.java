package net.chocomint.more_ores.block.custom;

import net.chocomint.more_ores.block.ModBlocks;
import net.chocomint.more_ores.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.*;

import static net.chocomint.more_ores.util.PedestalMap.ITEM_ENTITY_MAP;

public class PedestalBlock extends Block {

	// Voxel Shapes
	public static final VoxelShape BASE     = Block.createCuboidShape(4, 0, 4, 12, 2, 12);
	public static final VoxelShape PILLAR   = Block.createCuboidShape(6, 2, 6, 10, 11, 10);
	public static final VoxelShape TOP_BASE = Block.createCuboidShape(4, 11, 4, 12, 12, 12);
	public static final VoxelShape TOP1     = Block.createCuboidShape(4, 12, 3, 12, 14, 4);
	public static final VoxelShape TOP2     = Block.createCuboidShape(3, 12, 4, 4, 14, 12);
	public static final VoxelShape TOP3     = Block.createCuboidShape(4, 12, 12, 12, 14, 13);
	public static final VoxelShape TOP4     = Block.createCuboidShape(12, 12, 4, 13, 14, 12);

	public PedestalBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.union(BASE, PILLAR, TOP_BASE, TOP1, TOP2, TOP3, TOP4);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient()) {

			ItemEntity itemOnPedestal = ITEM_ENTITY_MAP.getOrDefault(pos, null);
			ItemStack handStack = player.getMainHandStack();

			if (handStack.isEmpty()) {
				if (itemOnPedestal != null) {
					player.setStackInHand(Hand.MAIN_HAND, itemOnPedestal.getStack());
					itemOnPedestal.kill();
					ITEM_ENTITY_MAP.remove(pos);
				}
			}
			else {
				ItemEntity ie = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, handStack, 0, 0, 0);
				ie.setPickupDelayInfinite();

				if (itemOnPedestal != null) {
					player.setStackInHand(Hand.MAIN_HAND, itemOnPedestal.getStack());
					itemOnPedestal.kill();
					ITEM_ENTITY_MAP.remove(pos);
				}
				else player.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.AIR));

				world.spawnEntity(ie);
				ITEM_ENTITY_MAP.put(pos, ie);
			}
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		ItemEntity itemOnPedestal = ITEM_ENTITY_MAP.getOrDefault(pos, null);

		if (itemOnPedestal != null) {
			itemOnPedestal.setPickupDelay(10);
			ITEM_ENTITY_MAP.remove(pos);
		}
		super.onBreak(world, pos, state, player);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

		ItemEntity itemOnPedestal = ITEM_ENTITY_MAP.getOrDefault(pos, null);

		if (itemOnPedestal != null) {

			ItemStack stack = itemOnPedestal.getStack();
			Rarity r = stack.getRarity();

			if (r != Rarity.COMMON) {

				ParticleEffect particle = switch (r) {
					case UNCOMMON -> ParticleTypes.FALLING_WATER;
					case RARE -> ParticleTypes.ENCHANT;
					case EPIC -> ParticleTypes.PORTAL;
					default -> throw new IllegalStateException("Unexpected value: " + r);
				};
				for (int i = 0; i < 5; i++) {
					world.addParticle(particle, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5,
							(random.nextDouble() - 0.5) * 1.4,
							(random.nextDouble() - 0.2) * 1.3,
							(random.nextDouble() - 0.5) * 1.4);
				}
			}

			if (isItemOnPedestal(world, pos, ModBlocks.DIAMOND_PEDESTAL, ModItems.PIERRESITVIA_ALLOY)) {
				List<Vec3i> relatePos = List.of(
						new Vec3i(2, 0, 2), new Vec3i(2, 0, -2),
						new Vec3i(-2, 0, 2), new Vec3i(-2, 0, -2),
						new Vec3i(2, 1, 2), new Vec3i(2, 1, -2),
						new Vec3i(-2, 1, 2), new Vec3i(-2, 1, -2)
				);
				for (Vec3i v : relatePos)
					if (world.getBlockState(pos.add(v)).getBlock() != Blocks.STONE_BRICK_WALL) return;

				Set<Item> set = new HashSet<>();
				List<Vec3i> upPedestal = List.of(
						new Vec3i(2, 2, 2), new Vec3i(2, 2, -2),
						new Vec3i(-2, 2, 2), new Vec3i(-2, 2, -2)
				);
				for (Vec3i v : upPedestal) {
					if (ITEM_ENTITY_MAP.get(pos.add(v)) != null &&
							world.getBlockState(pos.add(v)).getBlock() == ModBlocks.STONE_BRICKS_PEDESTAL)
						set.add(ITEM_ENTITY_MAP.get(pos.add(v)).getStack().getItem());
					else return;
				}
				if (set.equals(Set.of(Items.DIAMOND, Items.EMERALD, Items.NETHERITE_INGOT, Items.GOLD_INGOT))) {
					double v = 1.7;
					for (int i = 0; i < 5; i++) {
						world.addParticle(ParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, v, v, v);
						world.addParticle(ParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, v, v, -v);
						world.addParticle(ParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, -v, v, v);
						world.addParticle(ParticleTypes.PORTAL, pos.getX() + 0.5, pos.getY() + 0.8, pos.getZ() + 0.5, -v, v, -v);
					}
				}
			}
		}
	}

	private boolean isItemOnPedestal(World world, BlockPos pos, Block pedestalBlock, Item stackOnPedestal) {
		ItemStack stack = getStack(pos);
		return stack != null && world.getBlockState(pos).getBlock() == pedestalBlock && stack.getItem() == stackOnPedestal;
	}

	private ItemStack getStack(BlockPos pos) {
		ItemEntity ie = ITEM_ENTITY_MAP.getOrDefault(pos, null);
		return ie == null ? null : ie.getStack();
	}
}
