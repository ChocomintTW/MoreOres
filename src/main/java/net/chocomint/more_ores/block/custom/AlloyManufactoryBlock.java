package net.chocomint.more_ores.block.custom;

import net.chocomint.more_ores.block.entity.AlloyManufactoryBlockEntity;
import net.chocomint.more_ores.block.entity.ModBlockEntities;
import net.chocomint.more_ores.item.ModItems;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AlloyManufactoryBlock extends BlockWithEntity implements BlockEntityProvider {

	public AlloyManufactoryBlock(Settings settings) {
		super(settings);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new AlloyManufactoryBlockEntity(pos, state);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		//With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
		return BlockRenderType.MODEL;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			//This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
			//a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
			NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

			AlloyManufactoryBlockEntity blockEntity = (AlloyManufactoryBlockEntity)world.getBlockEntity(pos);

			if(blockEntity.getLava() <= 4000) {
				if (player.getMainHandStack().getItem() == Items.LAVA_BUCKET) {

					if (!player.isCreative()) {
						player.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.BUCKET, 1));
					}
					blockEntity.setLava(blockEntity.getLava() + 1000);
				}
				else if (player.getMainHandStack().getItem() == ModItems.LAVA_TANK
						&& player.getMainHandStack().getOrCreateNbt().getInt("fluid") >= 1000) {

					if (!player.isCreative()) {
						NbtCompound nbt = player.getMainHandStack().getOrCreateNbt();
						nbt.putInt("fluid", player.getMainHandStack().getOrCreateNbt().getInt("fluid") - 1000);
						player.getMainHandStack().setNbt(nbt);
					}
					blockEntity.setLava(blockEntity.getLava() + 1000);
				}
				else if (screenHandlerFactory != null) {
					//With this call the server will request the client to open the appropriate Screenhandler
					player.openHandledScreen(screenHandlerFactory);
				}
			}
			else {
				player.openHandledScreen(screenHandlerFactory);
			}
		}
		return ActionResult.SUCCESS;
	}


	//This method will drop all items onto the ground when the block is broken
	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof AlloyManufactoryBlockEntity) {
				ItemScatterer.spawn(world, pos, (AlloyManufactoryBlockEntity)blockEntity);
				// update comparators
				world.updateComparators(pos,this);
			}
			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, ModBlockEntities.ALLOY_MANUFACTORY_BLOCK_ENTITY, AlloyManufactoryBlockEntity::tick);
	}
}
