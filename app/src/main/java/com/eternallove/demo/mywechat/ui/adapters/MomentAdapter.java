package com.eternallove.demo.mywechat.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.eternallove.demo.mywechat.R;
import com.eternallove.demo.mywechat.modle.AutoWrapLineLayout;
import com.eternallove.demo.mywechat.modle.CommentBean;
import com.eternallove.demo.mywechat.modle.GeneralBean;
import com.eternallove.demo.mywechat.modle.HeadBean;
import com.eternallove.demo.mywechat.modle.LikeBean;
import com.eternallove.demo.mywechat.modle.LinkBean;
import com.eternallove.demo.mywechat.util.DateUtil;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.data;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/7
 */
public class MomentAdapter
        extends RecyclerView.Adapter<MomentAdapter.FriendCircleViewHolder> {

    private static final int TYPE_HEAD = 0x0;
    private static final int TYPE_GENERAL = 0x1;

    private HeadBean mHeadBean;
    private List<GeneralBean> mGeneralBeanList;

    private Context mContext;

    public MomentAdapter(Context context,
                         HeadBean headBean,
                         List<GeneralBean> generalBeanList) {
        mHeadBean = headBean;
        mGeneralBeanList = generalBeanList;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEAD : TYPE_GENERAL;
    }

    @Override
    public FriendCircleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            return new HeadViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_head, parent, false));
        }else {
            return new GeneralViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_general, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(FriendCircleViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEAD) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            this.onBindHeadViewHolder(headViewHolder);
        } else {
            GeneralViewHolder generalViewHolder = (GeneralViewHolder) holder;
            GeneralBean generalBean = mGeneralBeanList.get(position - 1);
            Glide.with(mContext)
                    .load(Uri.parse(generalBean.getAvatar()))
                    .placeholder(R.color.colorImagePlaceHolder)
                    .into(generalViewHolder.avatar);
            //填充用户名
            generalViewHolder.name.setText(generalBean.getName());
            //填充用户发布信息
            if(generalBean.getContent() != null){
                generalViewHolder.content.setText(generalBean.getContent());
                generalViewHolder.content.setVisibility(View.VISIBLE);
            } else {
                generalViewHolder.content.setVisibility(View.GONE);
            }
            //填充用户发布的连接信息
            if(generalBean.getLinkData()!=null){
                final LinkBean linkData = generalBean.getLinkData();
                Glide.with(mContext)
                        .load(linkData.getLinkimg())
                        .placeholder(R.color.colorImagePlaceHolder)
                        .into(generalViewHolder.linkimg);
                generalViewHolder.linkTitle.setText(linkData.getLinktitle());
                generalViewHolder.linkTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Toast.makeText(mContext,"将要跳转到:"+linkData.getUrl(),Toast.LENGTH_SHORT).show();
                    }
                });
                generalViewHolder.link.setVisibility(View.VISIBLE);
            }else{
                generalViewHolder.link.setVisibility(View.GONE);
            }
            //填充用户发布图片信息
            if (generalBean.getImageList() != null && generalBean.getImageList().size() > 0) {
                this.onBindGeneralImgViewHolder(generalViewHolder,generalBean);
                generalViewHolder.gridLayout.setVisibility(View.VISIBLE);
            } else {
                generalViewHolder.gridLayout.setVisibility(View.GONE);
            }
            //填充评论点赞数据
            boolean isShowComentOrLike = false;
            generalViewHolder.like.removeAllViews();
            generalViewHolder.comment.removeAllViews();
            if(generalBean.getLikeData()!= null) {
                generalViewHolder.like.setFillMode(AutoWrapLineLayout.MODE_WRAP_CONTENT);
                for(LikeBean item : generalBean.getLikeData()) {
                    View likeItem = LayoutInflater.from(mContext).inflate(R.layout.item_general_like, null);
                    TextView userName = (TextView) likeItem.findViewById(R.id.textView_item_general_like_username);
                    userName.setText(item.getUsername());
                    generalViewHolder.like.addView(likeItem);
                }
                isShowComentOrLike = true;
            }
            if(generalBean.getCommentData()!=null){
                for(CommentBean item : generalBean.getCommentData()){
                    View commentItem = LayoutInflater.from(mContext).inflate(R.layout.item_general_comment, null);
                    TextView initiatorName = (TextView) commentItem.findViewById(R.id.textView_item_general_comment_initiatorName);
                    TextView reply = (TextView) commentItem.findViewById(R.id.textView_item_general_comment_1);
                    TextView recipientName = (TextView) commentItem.findViewById(R.id.textView_item_general_comment_recipientName);
                    TextView commentContent = (TextView) commentItem.findViewById(R.id.textView_item_general_comment_content);
                    if(item.getRecipient_name()!=null){
                        recipientName.setText(item.getRecipient_name());
                    }
                    else{
                        reply.setVisibility(View.GONE);
                        recipientName.setVisibility(View.GONE);
                    }
                    commentContent.setText(item.getContent());
                    initiatorName.setText(item.getInitiator_name());
                    generalViewHolder.comment.addView(commentItem);
                }
                isShowComentOrLike = true;
            }
            if(isShowComentOrLike){
                generalViewHolder.likeOrComment.setVisibility(View.VISIBLE);
            }else{
                generalViewHolder.likeOrComment.setVisibility(View.GONE);
            }
            generalViewHolder.date
                    .setText(DateUtil.getDateString(mContext, generalBean.getPublishDate()));
            generalViewHolder.commentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(mContext,view);
                    popupMenu.getMenuInflater()
                            .inflate(R.menu.menu_comment,popupMenu.getMenu());
                    popupMenu.setGravity(Gravity.LEFT);
                    popupMenu.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mGeneralBeanList.size() + 1;
    }

    private void onBindHeadViewHolder(HeadViewHolder headViewHolder){
        Glide.with(mContext)
                .load(mHeadBean.getHeadAvatar())
                .placeholder(R.color.colorImagePlaceHolder)
                .into(headViewHolder.avatar);
        Glide.with(mContext)
                .load(mHeadBean.getHeadBackground())
                .centerCrop()
                .placeholder(R.color.colorImagePlaceHolder)
                .into(headViewHolder.background);
        headViewHolder.name.setText(mHeadBean.getHeadName());
    }

    private void onBindGeneralImgViewHolder(GeneralViewHolder generalViewHolder,GeneralBean generalBean){
        List<String> imageList = generalBean.getImageList();

        int size = imageList.size();
        int rowCount = (int) Math.sqrt(size);
        int columnCount = size / rowCount;

        generalViewHolder.gridLayout.setRowCount(rowCount);
        generalViewHolder.gridLayout.setColumnCount(columnCount);

        int k = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (k >= size || k >= 9) {
                    // 超过总数
                    break;
                }
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                layoutParams.setGravity(Gravity.CENTER);
                layoutParams.rowSpec = GridLayout.spec(i);
                layoutParams.columnSpec = GridLayout.spec(j);

                if (size == 1) {
                    // 如果只有一张就完整显示
                    layoutParams.width = WRAP_CONTENT;
                    layoutParams.height = WRAP_CONTENT;

                    ImageView imageView = new ImageView(mContext);
                    imageView.setMinimumHeight((int) mContext.getResources()
                            .getDimension(R.dimen.item_general_image_grid_image_size));
                    imageView.setMinimumWidth((int) mContext.getResources()
                            .getDimension(R.dimen.item_general_image_grid_image_size));

                    imageView.setLayoutParams(layoutParams);
                    generalViewHolder.gridLayout.addView(imageView);

                    final int maxWidth = (int) mContext.getResources()
                            .getDimension(R.dimen.item_general_image_grid_image_max_width);

                    Glide.with(mContext)
                            .load(imageList.get(k))
                            .asBitmap()
                            .transform(new Transformation<Bitmap>() {
                                @Override
                                public Resource<Bitmap> transform(Resource<Bitmap> resource,
                                                                  int outWidth,
                                                                  int outHeight) {
                                    int height = resource.get().getHeight();
                                    int width = resource.get().getWidth();
                                    if (width > maxWidth) {
                                        int time = width / maxWidth;
                                        width /= time;
                                        height /= time;
                                    }
                                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                                            resource.get(), width, height, false);

                                    return new SimpleResource<>(resizedBitmap);
                                }

                                @Override
                                public String getId() {
                                    return "";
                                }
                            })
                            .placeholder(R.color.colorImagePlaceHolder)
                            .into(imageView);
                } else {
                    // 否则就显示为一个方块
                    layoutParams.width = (int) mContext.getResources()
                            .getDimension(R.dimen.item_general_image_grid_image_size);
                    layoutParams.height = (int) mContext.getResources()
                            .getDimension(R.dimen.item_general_image_grid_image_size);

                    int margin = (int) mContext.getResources()
                            .getDimension(R.dimen.item_general_image_grid_image_margin);
                    layoutParams.setMargins(margin, margin, margin, margin);

                    ImageView imageView = new ImageView(mContext);
                    imageView.setLayoutParams(layoutParams);
                    generalViewHolder.gridLayout.addView(imageView);

                    Glide.with(mContext)
                            .load(imageList.get(k))
                            .placeholder(R.color.colorImagePlaceHolder)
                            .centerCrop()
                            .into(imageView);
                }
                k++;
            }
        }
    }
    static class FriendCircleViewHolder extends RecyclerView.ViewHolder {

        FriendCircleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeadViewHolder extends FriendCircleViewHolder {

        @BindView(R.id.textView_item_head_name)
        TextView name;

        @BindView(R.id.imageView_item_head_background)
        ImageView background;

        @BindView(R.id.imageView_item_head_avatar)
        ImageView avatar;

        HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class GeneralViewHolder extends FriendCircleViewHolder {

        @BindView(R.id.textView_item_general_name)
        TextView name;

        @BindView(R.id.textView_item_general_content)
        TextView content;

        @BindView(R.id.imageView_item_general_avatar)
        ImageView avatar;

        @BindView(R.id.linearLayout_item_link_content)
        LinearLayout link;

        @BindView(R.id.imageView_item_link_img)
        ImageView linkimg;

        @BindView(R.id.textview_item_link_title)
        TextView linkTitle;

        @BindView(R.id.gridLayout_item_general)
        GridLayout gridLayout;

        @BindView(R.id.textView_item_general_date)
        TextView date;

        @BindView(R.id.imageButton_item_general_comment)
        ImageView commentButton;

        @BindView(R.id.linearlayout_likeOrComment)
        LinearLayout likeOrComment;

        @BindView(R.id.autoWrapLineLayout_like)
        AutoWrapLineLayout like;

        @BindView(R.id.linearlayout_comment)
        LinearLayout comment;

        GeneralViewHolder(View itemView) {
            super(itemView);
        }
    }
}
