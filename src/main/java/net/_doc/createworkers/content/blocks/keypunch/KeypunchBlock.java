package net._doc.createworkers.content.blocks.keypunch;

import com.simibubi.create.AllShapes;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.foundation.block.IBE;

import net._doc.createworkers.CWBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

public class KeypunchBlock extends Block implements IBE<KeypunchBlockEntity> {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public KeypunchBlock(Properties pProperties) {
		super(pProperties);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getPlayer() != null) {
			Direction nearestLookingDirection = context.getHorizontalDirection();
			return defaultBlockState().setValue(FACING,
					context.getPlayer() != null && context.getPlayer().isShiftKeyDown() ? nearestLookingDirection
							: nearestLookingDirection.getOpposite());
		}
		return defaultBlockState().setValue(FACING, Direction.NORTH);
	}

	@Override
	public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_,
			CollisionContext p_220053_4_) {
		return AllShapes.CASING_13PX.get(Direction.UP);
	}

	@Override
	public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
			BlockHitResult pHit) {

		InteractionResult result = onBlockEntityUse(pLevel, pPos, station -> {
			if (pLevel.isClientSide)
				return InteractionResult.PASS;
			station.notifyUpdate();
			AllSoundEvents.playItemPickup(pPlayer);
			return InteractionResult.SUCCESS;
		});

		if (result == InteractionResult.PASS)
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
					() -> () -> withBlockEntityDo(pLevel, pPos, be -> this.displayScreen(be, pPlayer)));
		return InteractionResult.SUCCESS;
	}

	@OnlyIn(value = Dist.CLIENT)
	protected void displayScreen(KeypunchBlockEntity be, Player player) {
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public BlockEntityType<? extends KeypunchBlockEntity> getBlockEntityType() {
		return CWBlockEntities.KEYPUNCH.get();
	}

	@Override
	public Class<KeypunchBlockEntity> getBlockEntityClass() {
		return KeypunchBlockEntity.class;
	}
}
