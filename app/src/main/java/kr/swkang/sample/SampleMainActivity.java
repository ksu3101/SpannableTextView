package kr.swkang.sample;

import android.app.Activity;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.view.View;
import android.widget.Toast;

import kr.swkang.spannabletextview.SpannableTextView;
import kr.swkang.spannabletextview.utils.SwClickableSpan;

/**
 * @author KangSung-Woo
 * @since 2016/07/15
 */
public class SampleMainActivity
    extends Activity {
  private static final String TAG = SampleMainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sample_main_activity);

    SpannableTextView stv1 = (SpannableTextView) findViewById(R.id.stv_1);

    // Span인스턴스를 따로 만들고 관리 하는 방법.
    final SpannableTextView.Span span = new SpannableTextView.Span("Hello World!!! ")
        .textColor(Color.GRAY)
        .bold()
        .textSizeSP(26)
        .build();
    stv1.addSpan(span);

    // 바로 Span인스턴스를 생성하고 항목을 설정 하는 방법
    stv1.addSpan(
        new SpannableTextView.Span("\n반가워요!! ")
            .textColorRes(R.color.colorAccent)
            .italic()
            .textSizePX(getResources().getDimensionPixelSize(R.dimen.textsize_def))
            .build()
    );
    stv1.addSpan(
        new SpannableTextView.Span(" :)  ")
            .textColor(Color.rgb(100, 100, 100))
            .build()
    );

    // 클릭 이벤트의 구현
    stv1.addSpan(
        new SpannableTextView.Span("(Click Link)")
            .click(
                new SwClickableSpan() {
                  @Override
                  public void onClick(View widget) {
                    Toast.makeText(SampleMainActivity.this, "Touched link one.", Toast.LENGTH_SHORT).show();
                  }
                }
            )
            .linkTextColorRes(R.color.colorPrimary)
            .build()
    );

    stv1.addSpan(
        new SpannableTextView.Span(" // ")
            .build()
    );

    stv1.addSpan(
        new SpannableTextView.Span("(Touch this)")
            .click(
                new SwClickableSpan() {
                  @Override
                  public void onClick(View widget) {
                    Toast.makeText(SampleMainActivity.this, "Touched link two.", Toast.LENGTH_SHORT).show();
                  }

                  @Override
                  public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);

                    // hide link under line
                    ds.setUnderlineText(false);
                  }
                }
            )
            .linkTextColorRes(R.color.colorPrimaryDark)
            .build()
    );

    stv1.addSpan(
        new SpannableTextView.Span("\nscale X(2.0f)")
            .textSizeSP(16)
            .scaleX(2.0f)
            .build()
    );

    //stv1.clearSpans();

    // 텍스트에 블러 마스크 추가
    SpannableTextView stv2 = (SpannableTextView) findViewById(R.id.stv_2);
    stv2.addSpan(
        new SpannableTextView.Span("Blurred Text (Normal)")
            .textSizeSP(20)
            .blurMaskFilter(5)
            .build()
    );
    SpannableTextView stv22 = (SpannableTextView) findViewById(R.id.stv_2_2);
    stv22.addSpan(
        new SpannableTextView.Span("Blurred Text (Outer)")
            .textSizeSP(30)
            .blurMaskFilter(8, BlurMaskFilter.Blur.OUTER)
            .build()
    );

    SpannableTextView stv3 = (SpannableTextView) findViewById(R.id.stv_3);
    stv3.addSpan(
        new SpannableTextView.Span(R.string.rtext1)
            .textSizeSP(16)
            .textColor(Color.BLACK)
            .bold()
            .build()
    );

    SpannableTextView stv4 = (SpannableTextView) findViewById(R.id.stv_4);
    stv4.addSpan(
        new SpannableTextView.Span(R.string.rtext2)
            .textSizeSP(12)
            .textColor(Color.GRAY)
            .findSharpTags(
                Color.RED
            )
            .findAtTags(
                // normal text color, pressed text color
                new SwClickableSpan(Color.rgb(39, 174, 96), Color.rgb(211, 84, 0)) {
                  @Override
                  public void onClick(View widget) {
                    Toast.makeText(SampleMainActivity.this, "@ Tag clicked..", Toast.LENGTH_SHORT).show();
                  }
                }
            )
            .findURLstrings(
                // hide underline
                new SwClickableSpan(false) {
                  @Override
                  public void onClick(View widget) {
                    Toast.makeText(SampleMainActivity.this, "URL string clicked..", Toast.LENGTH_SHORT).show();
                  }
                }
            )
            .build()
    );

  }

}
