package kr.swkang.spannabletextview;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.swkang.spannabletextview.utils.TextDimenTyped;

/**
 * Spannable TextView
 *
 * @author KangSung-Woo
 * @since 2016/07/15
 */
public class SpannableTextView
    extends TextView {
  public static       int DEFAULT_TEXT_SIZE       = 0;
  public static final int DEFAULT_TEXT_COLOR      = Color.BLACK;
  public static final int DEFAULT_LINK_TEXT_COLOR = -1;
  public static final int DEFAULT_TEXT_BG_COLOR   = -1;

  private static Context         context;
  private        DisplayMetrics  displayMetrics;
  private        ArrayList<Span> spans;

  public SpannableTextView(Context context) {
    super(context);
    initializeView();
  }

  public SpannableTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initializeView();
  }

  public SpannableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initializeView();
  }

  private void initializeView() {
    context = getContext();
    spans = new ArrayList<>();
    displayMetrics = getContext().getResources().getDisplayMetrics();
    SpannableTextView.DEFAULT_TEXT_SIZE = (int) getTextSize();
  }

  public void clearSpans() {
    this.spans.clear();
    setText("");
  }

  public int addSpan(@NonNull Span span) {
    this.spans.add(span);
    updateTextView();
    return spans.size();
  }

  public int addSpan(@NonNull Span span, int insertPosition) {
    if (checkIndex(insertPosition)) {
      this.spans.add(insertPosition, span);
      updateTextView();
      return insertPosition;
    }
    return -1;
  }

  public Span removeSpan(int removeSpanPosition) {
    if (checkIndex(removeSpanPosition)) {
      return this.spans.remove(removeSpanPosition);
    }
    return null;
  }

  public void replaceSpanAt(int replacePosition, @NonNull Span newSpan) {
    if (checkIndex(replacePosition)) {
      this.spans.set(replacePosition, newSpan);
    }
  }

  public Span getSpan(int position) {
    if (checkIndex(position)) {
      return this.spans.get(position);
    }
    return null;
  }

  public int getSpansCount() {
    if (spans == null) return -1;
    return spans.size();
  }

  public void updateTextView() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < spans.size(); i++) {
      Span span = spans.get(i);
      builder.append(span.text);
    }

    int cursor = 0;
    SpannableString spannableString = new SpannableString(builder.toString());
    for (int i = 0; i < spans.size(); i++) {
      Span span = spans.get(i);

      // text size
      int textSize = (int) TypedValue.applyDimension(span.typedValue.getValue(), span.textSize, displayMetrics);
      spannableString.setSpan(
          new AbsoluteSizeSpan(textSize),
          cursor,
          cursor + span.text.length(),
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
      );

      // type face (bold, italic)
      spannableString.setSpan(
          new StyleSpan(span.bold && span.italic ? Typeface.BOLD_ITALIC :
                            (span.bold ? Typeface.BOLD :
                                (span.italic ? Typeface.ITALIC : Typeface.NORMAL))),
          cursor,
          cursor + span.text.length(),
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
      );

      // text color
      spannableString.setSpan(
          new ForegroundColorSpan(span.textColor),
          cursor,
          cursor + span.text.length(),
          Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
      );

      // background color
      if (span.textBackgroundColor != -1) {
        spannableString.setSpan(
            new BackgroundColorSpan(span.textBackgroundColor),
            cursor,
            cursor + span.text.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
      }

      if (span.superScript) {
        spannableString.setSpan(
            new SuperscriptSpan(),
            cursor,
            cursor + span.text.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
      }

      if (span.subScript) {
        spannableString.setSpan(
            new SubscriptSpan(),
            cursor,
            cursor + span.text.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
      }

      if (span.strike) {
        spannableString.setSpan(
            new StrikethroughSpan(),
            cursor,
            cursor + span.text.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
      }

      if (span.underline) {
        spannableString.setSpan(
            new UnderlineSpan(),
            cursor,
            cursor + span.text.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
      }

      // click event
      if (span.clickableSpan != null) {
        spannableString.setSpan(
            span.clickableSpan,
            cursor,
            cursor + span.text.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        setLinkTextColor(Color.BLUE);
        setHighlightColor(span.linkHighlightColor);
        setMovementMethod(LinkMovementMethod.getInstance());
      }
      setLinksClickable(span.linkClickEnable);

      if (span.linkTextColor != -1) {
        setLinkTextColor(span.linkTextColor);
      }

      // scale x
      if (span.scaleX > 0) {
        spannableString.setSpan(
            new ScaleXSpan(span.scaleX),
            cursor,
            cursor + span.text.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );
      }

      // blur mask filter
      if (span.blurMaskFilter != null) {
        spannableString.setSpan(
            new MaskFilterSpan(span.blurMaskFilter),
            cursor,
            cursor + span.text.length(),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
      }

      // emboss mask filter
      if (span.embossMaskFilter != null) {
        spannableString.setSpan(
            new MaskFilterSpan(span.embossMaskFilter),
            cursor,
            cursor + span.text.length(),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
      }

      // type face
      if (span.typeFace != null && !span.typeFace.isEmpty()) {
        spannableString.setSpan(
            new TypefaceSpan(span.typeFace),
            cursor,
            cursor + span.text.length(),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
      }

      cursor = cursor + span.text.length();
    }

    setText(spannableString);
  }

  public boolean checkIndex(int index) {
    if (index < 0 && index > spans.size()) {
      throw new IndexOutOfBoundsException("Invalid index " + index + ", size is " + spans.size());
    }
    return true;
  }


  public static class Span {
    private String           text;
    private int              textSize;
    private TextDimenTyped   typedValue;
    private int              textColor;
    private int              linkTextColor;
    private int              textBackgroundColor;
    private boolean          bold;
    private boolean          italic;
    private boolean          underline;
    private boolean          strike;
    private boolean          superScript;
    private boolean          subScript;
    private ClickableSpan    clickableSpan;
    private int              linkHighlightColor;
    private boolean          linkClickEnable;
    private float            scaleX;
    private BlurMaskFilter   blurMaskFilter;
    private EmbossMaskFilter embossMaskFilter;
    private String           typeFace;

    public Span(@NonNull String text) {
      this.text = text;
      init();
    }

    public Span(@StringRes int textResId) {
      if (checkContextInstance()) {
        this.text = context.getString(textResId);
      }
      init();
    }

    private void init() {
      this.textSize = SpannableTextView.DEFAULT_TEXT_SIZE;
      this.typedValue = TextDimenTyped.PX;
      this.textColor = DEFAULT_TEXT_COLOR;
      this.linkTextColor = DEFAULT_LINK_TEXT_COLOR;
      this.textBackgroundColor = DEFAULT_TEXT_BG_COLOR;
      this.bold = false;
      this.italic = false;
      this.underline = false;
      this.strike = false;
      this.superScript = false;
      this.subScript = false;
      this.clickableSpan = null;
      this.linkHighlightColor = DEFAULT_LINK_TEXT_COLOR;
      this.linkClickEnable = true;
      this.scaleX = 0.0f;
      this.blurMaskFilter = null;
      this.embossMaskFilter = null;
      this.typeFace = null;
    }

    public Span text(@NonNull String text) {
      this.text = text;
      return this;
    }

    public Span textSizeSP(int textSizeSp) {
      return textSize(TextDimenTyped.SP, textSizeSp);
    }

    public Span textSizeDIP(int textSizeDIP) {
      return textSize(TextDimenTyped.DIP, textSizeDIP);
    }

    public Span textSizeDIPRes(@DimenRes int textSizeResId) {
      if (checkContextInstance()) {
        return textSize(TextDimenTyped.DIP, context.getResources().getDimensionPixelSize(textSizeResId));
      }
      return this;
    }

    public Span textSizePX(int textSizePx) {
      return textSize(TextDimenTyped.PX, textSizePx);
    }

    public Span textSizePT(int textSizePT) {
      return textSize(TextDimenTyped.PT, textSizePT);
    }

    public Span textSize(TextDimenTyped typedValue, int textSize) {
      this.textSize = textSize;
      this.typedValue = typedValue;
      return this;
    }

    public Span textColor(@ColorInt int textColor) {
      this.textColor = textColor;
      return this;
    }

    public Span textColorRes(@ColorRes int textColorResId) {
      if (checkContextInstance()) {
        this.textColor = ContextCompat.getColor(context, textColorResId);
      }
      return this;
    }

    public Span linkTextColor(@ColorInt int linkTextColor) {
      this.linkTextColor = linkTextColor;
      return this;
    }

    public Span linkTextColorRes(@ColorRes int linkTextColorResId) {
      if (checkContextInstance()) {
        this.linkTextColor = ContextCompat.getColor(context, linkTextColorResId);
      }
      return this;
    }

    public Span textBackgroundColor(@ColorInt int bgColor) {
      this.textBackgroundColor = bgColor;
      return this;
    }

    public Span textBackgroundColorRes(@ColorRes int bgColorResId) {
      if (checkContextInstance()) {
        this.textBackgroundColor = ContextCompat.getColor(context, bgColorResId);
      }
      return this;
    }

    public Span bold() {
      this.bold = true;
      return this;
    }

    public Span unBold() {
      this.bold = false;
      return this;
    }

    public Span italic() {
      this.italic = true;
      return this;
    }

    public Span unItalic() {
      this.italic = false;
      return this;
    }

    public Span underline() {
      this.underline = true;
      return this;
    }

    public Span unUnderline() {
      this.underline = false;
      return this;
    }

    public Span strike() {
      this.strike = true;
      return this;
    }

    public Span unStrike() {
      this.strike = false;
      return this;
    }

    public Span superScript() {
      this.superScript = true;
      return this;
    }

    public Span unSuperScript() {
      this.subScript = false;
      return this;
    }

    public Span subScript() {
      this.subScript = true;
      return this;
    }

    public Span unSubScript() {
      this.subScript = false;
      return this;
    }

    public Span click(@NonNull ClickableSpan clickableSpan) {
      this.clickableSpan = clickableSpan;
      return this;
    }

    public Span clickHighlightColor(@ColorInt int linkHighlightColor) {
      this.linkHighlightColor = linkHighlightColor;
      return this;
    }

    public Span clickHighlightColorRes(@ColorRes int linkHighlightColorResId) {
      if (checkContextInstance()) {
        this.textBackgroundColor = ContextCompat.getColor(context, linkHighlightColorResId);
      }
      return this;
    }

    public Span linkClickEnable() {
      this.linkClickEnable = true;
      return this;
    }

    public Span linkClickDisable() {
      this.linkClickEnable = false;
      return this;
    }

    public Span scaleX(float scaleX) {
      this.scaleX = scaleX;
      return this;
    }

    public Span blurMaskFilter(@FloatRange(from = 0) float radius) {
      this.blurMaskFilter = new BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL);
      return this;
    }

    public Span blurMaskFilter(@FloatRange(from = 0) float radius, BlurMaskFilter.Blur blurStyle) {
      this.blurMaskFilter = new BlurMaskFilter(radius, blurStyle);
      return this;
    }

    public Span embossMaskFilter(@NonNull float[] direction, float ambient, float specular, float blurRadius) {
      this.embossMaskFilter = new EmbossMaskFilter(direction, ambient, specular, blurRadius);
      return this;
    }

    public Span typeFaceFamily(@NonNull String fontFamily) {
      this.typeFace = fontFamily;
      return this;
    }

    public Span build() {
      return this;
    }

    public Span build(int specificIndex) {
      return this;
    }

    private boolean checkContextInstance() {
      if (context != null) {
        return true;
      }
      else {
        throw new NullPointerException("Context instace is null..");
      }
    }

  }

}