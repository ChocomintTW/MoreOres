package net.chocomint.more_ores.block.custom;

import net.chocomint.more_ores.block.entity.ATMBlockEntity;
import net.chocomint.more_ores.block.entity.ModBlockEntities;
import net.chocomint.more_ores.block.multiBlock.ATMPart;
import net.chocomint.more_ores.item.ModItems;
import net.chocomint.more_ores.item.custom.CreditCardItem;
import net.chocomint.more_ores.util.Enums;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static net.chocomint.more_ores.item.custom.CreditCardItem.*;

public class ATMBlock extends BlockWithEntity implements BlockEntityProvider {
	public static final DirectionProperty FACING = DirectionProperty.of("facing");
	public static final EnumProperty<ATMPart> PART = EnumProperty.of("part", ATMPart.class);

	public static String PLAYER;
	public static Enums.CardRank CARD;
	public static int COIN;
	public static int COST;
	public static Enums.CursorBehavior BEHAVIOR;

	private static final VoxelShape center = Block.createCuboidShape(1, 0, 1, 15, 32, 16);
	private static final VoxelShape left   = Block.createCuboidShape(0, 0, 0, 16, 32, 0);
	private static final VoxelShape right  = Block.createCuboidShape(16, 0, 0, 16, 32, 0);

	public ATMBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getDefaultState().with(PART, ATMPart.BASE));
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new ATMBlockEntity(pos, state);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		//With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
		return BlockRenderType.MODEL;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.union(center, left, right);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, PART);
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		world.setBlockState(pos, state.with(FACING, placer.getHorizontalFacing().getOpposite()));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if(!world.isClient()) {
			NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

			ItemStack stack = player.getMainHandStack();

			if(hand == Hand.MAIN_HAND && stack.getItem() == ModItems.CREDIT_CARD) {
				if(Objects.equals(stack.getOrCreateNbt().getString("ownerId"), "")) {
					player.sendMessage(new TranslatableText("message.credit_card.register_owner"), false);

					NbtCompound nbt = stack.getOrCreateNbt();
					nbt.putString("ownerId", player.getName().getString());
					stack.setNbt(nbt);
				}
				else {
					if(Objects.equals(player.getName().getString(), stack.getOrCreateNbt().getString("ownerId"))) {
						player.openHandledScreen(screenHandlerFactory);

						PLAYER = player.getName().getString();
						CARD = Enums.CardRank.values()[getCardRankOrder(stack)];
						COIN = getCoin(stack);
					}
					else {
						player.sendMessage(new TranslatableText("message.credit_card.has_owner",
								stack.getOrCreateNbt().getString("ownerId")), false);
					}
				}
			}
			else {
				player.sendMessage(new TranslatableText("message.credit_card.no_card"), false);
			}
		}

		return ActionResult.SUCCESS;
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof ATMBlockEntity) {
				ItemScatterer.spawn(world, pos, (ATMBlockEntity)blockEntity);
				// update comparators
				world.updateComparators(pos,this);
			}
			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return checkType(type, ModBlockEntities.ATM_BLOCK_ENTITY, ATMBlockEntity::tick);
	}

//	public static DoubleBlockProperties.Type getATMPart(BlockState state) {
//		BedPart bedPart = (BedPart)state.get(PART);
//		return bedPart == BedPart.HEAD ? DoubleBlockProperties.Type.FIRST : DoubleBlockProperties.Type.SECOND;
//	}
}
