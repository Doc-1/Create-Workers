package net._doc.createworkers.blocks.keypunch;

import com.simibubi.create.AllShapes;
import com.simibubi.create.foundation.block.IBE;

import net._doc.createworkers.gui.blocks.keypunch.LayerKeypunch;
import net._doc.createworkers.registeries.CWBlockEntities;
import net._doc.createworkers.registeries.CWGui;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
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
import team.creative.creativecore.common.gui.GuiLayer;
import team.creative.creativecore.common.gui.creator.BlockGuiCreator;

public class KeypunchBlock extends Block implements IBE<KeypunchBlockEntity>, BlockGuiCreator {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    
    public KeypunchBlock(Properties pProperties) {
        super(pProperties);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (context.getPlayer() != null) {
            Direction nearestLookingDirection = context.getHorizontalDirection();
            return defaultBlockState()
                    .setValue(FACING, context.getPlayer() != null && context.getPlayer().isShiftKeyDown() ? nearestLookingDirection : nearestLookingDirection.getOpposite());
        }
        return defaultBlockState().setValue(FACING, Direction.NORTH);
    }
    
    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return AllShapes.CASING_13PX.get(Direction.UP);
    }
    
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide)
            CWGui.KEYPUNCH.open(pPlayer, pPos);
        return InteractionResult.SUCCESS;
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
    
    @Override
    public GuiLayer create(CompoundTag nbt, Level level, BlockPos pos, BlockState state, Player player) {
        return new LayerKeypunch();
    }
}
