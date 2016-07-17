## SpannableTextView
 Spannable을 이용한 텍스트뷰 입니다. 텍스트 컬러, bold, italic 등 다양한 효과를 텍스트 뷰에 적용 할 수 있습니다. 지원되는 효과는 다음과 같습니다.
  - text color
  - link text color
  - bold (**BOLD**)
  - italic (*italic*)
  - bold, italic (***BOLD-italic***)
  - under line 
  - strke (~~strike~~)
  - super script 
  - sub script
  - clickable span string  

### TODO List  
- ver 0.0.2
  - [x] Blur Mask Filter (jcenter 업로드 예정)
  - [x] Emboss Mask Filter (jcenter 업로드 예정)
  - [x] Scale X (jcenter 업로드 예정)

- ver 0.0.3
  - [ ] '#', '@' 태그 링크 (style, click envent)
  - [ ] 사용자 `Pattern` 적용 (style, click envent)
  
### 1. screen shot
![screen shot image](https://github.com/ksu3101/TIL/blob/master/Android/images/spannable_tv_sample.jpg)
  
### 2. install
 아래의 내용을 app의 `build.gradle`에 추가 해 주세요. 
```
dependencies {
  compile 'kr.swkang.spannabletextview:libspannabletextview:0.0.1'
}
```  
   
---
### 3. use guide
 [Sample Activity](https://github.com/ksu3101/SpannableTextView/blob/master/app/src/main/java/kr/swkang/sample/SampleMainActivity.java)  
#### 3.1 사용 예
```java
    SpannableTextView stv1 = (SpannableTextView) findViewById(R.id.stv_1);

    final SpannableTextView.Span span = new SpannableTextView.Span("Hello World!!! ")
        .textColor(Color.GRAY)
        .bold()
        .textSizeSP(26)
        .build();
    stv1.addSpan(span);

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

    stv1.addSpan(
        new SpannableTextView.Span("(Click Link)")
            .click(
                new ClickableSpan() {
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
                new ClickableSpan() {
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
            .build()
    );
```

---
### 4. license
```
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 1):
 * <burkdog34@gmail.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return KangSung-Woo
 * ----------------------------------------------------------------------------
```
