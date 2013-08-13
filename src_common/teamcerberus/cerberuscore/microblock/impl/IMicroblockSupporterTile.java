package teamcerberus.cerberuscore.microblock.impl;

import java.util.List;

import teamcerberus.cerberuscore.microblock.MicroblockCoverSystem;
import teamcerberus.cerberuscore.microblock.part.EnumPosition;
import teamcerberus.cerberuscore.microblock.part.PartType;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**
 * A multipart tile that is compatible with microblocks.
 * Only tile entities should implement this.
 */
public interface IMicroblockSupporterTile {
	/**
	 * Returns the microblock position a tile-owned part mainly occupies, or null if
	 * unknown or the part occupies the whole block.
	 * Used to determine the position of new microblocks placed by the player.
	 * 
	 * @param subHit The tile-owned part ID to check.
	 * @return The position the tile-owned part occupies.
	 */
	public EnumPosition getPartPosition(int subHit);
	
	/**
	 * Returns true if the tile is "in the way" of this part and will prevent it being placed.
	 * @param type The type of part being placed.
	 * @param pos The position the part is being placed in.
	 * @return False to prevent part placement, otherwise true.
	 */
	public boolean isPlacementBlockedByTile(PartType<?> type, EnumPosition pos);

	/**
	 * Returns true if the given position is occupied by the tile.
	 * Does not check for collisions with other positions.
	 */
	public boolean isPositionOccupiedByTile(EnumPosition pos);
	
	/**
	 * Returns an IMicroblockCoverSystem object, or null if this tile does not support a cover system
	 * @see mods.immibis.core.api.multipart.IMicroblockCoverSystem 
	 */
	public MicroblockCoverSystem getCoverSystem();
	
	/**
	 * Gets the speed at which a player breaks a part.
	 * See Block.getPlayerRelativeBlockHardness. 
	 * @param ply The player.
	 * @param The part.
	 * @return The speed.
	 */
	float getPlayerRelativePartHardness(EntityPlayer ply, int part);
	
	/**
	 * Called when the player middle-clicks on a part.
	 * @param rayTrace The location under the player's crosshair.
	 * @param part The part number they middle-clicked on.
	 * @return The itemstack to pick.
	 */
	public ItemStack pickPart(MovingObjectPosition rayTrace, int part);
	
	/**
	 * Checks if a given face of this block is solid.
	 * Used to determine the result of Block.isBlockSolidOnSide.
	 */
	public boolean isSolidOnSide(ForgeDirection side);
	
	/**
	 * Renders all the parts.
	 * @param render The render context.
	 */
	@SideOnly(Side.CLIENT)
	public void render(RenderBlocks render);
	
	/**
	 * Renders one part.
	 * @param render The render context.
	 * @param part The part number.
	 */
	@SideOnly(Side.CLIENT)
	public void renderPart(RenderBlocks render, int part);
	
	/**
	 * Returns all collision boxes that intersect the given mask, in world coordinates.
	 * @param mask The mask AABB, in world coordinates.
	 * @param list The list to add the returned boxes to.
	 */
	public void getCollidingBoundingBoxes(AxisAlignedBB mask, List<AxisAlignedBB> list);

	/**
	 * Called when a player finishes breaking a part.
	 * @param ply The player breaking the part.
	 * @param subhit The part being broken.
	 * @return The item stacks dropped, or null if none.
	 */
	public List<ItemStack> removePartByPlayer(EntityPlayer ply, int part);
	
	/**
	 * Returns the AABB of a part, in tile-local coordinates.
	 * Currently used for drawing the selection box.
	 * May return null, in which case no selection box is drawn.
	 * 
	 * @param part The part ID to check.
	 * @return The AABB of the part, or null.
	 */
	public AxisAlignedBB getPartAABBFromPool(int part);
	
	/**
	 * Finds the closest intercept of the given ray with parts in this container.
	 * @param src The start of the ray, in world coordinates.
	 * @param dst The end of the ray, in world coordinates.
	 * @return A ray-trace result, or null if the ray does not intersect any parts.
	 */
	public MovingObjectPosition collisionRayTrace(Vec3 src, Vec3 dst);
	
	/**
	 * Called after microblocks are added or removed.
	 */
	public void onMicroblocksChanged();
}
