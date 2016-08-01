## SpannableTextView  
[![Download](https://api.bintray.com/packages/burkdog/maven/libspannabletextview/images/download.svg) ](https://bintray.com/burkdog/maven/libspannabletextview/_latestVersion)  
 ![screen shot image](https://github.com/ksu3101/TIL/blob/master/Android/images/spannable_tv_sample2.jpg)
   
 ---  
 Spannable을 이용한 텍스트뷰 입니다. 텍스트 컬러, bold, italic 등 다양한 효과를 텍스트 뷰에 적용 할 수 있습니다. 지원되는 효과는 다음과 같습니다.  
  - `text(String str)` : spannable을 적용할 텍스트 문구. 
  - `textSize(TextDimenTyped typed, int sizeValue)` : 텍스트의 크기와 단위(`SP, DIP, PX, PT`)를 설정 합니다. 
   - `textSizeSP()` : SP단위의 텍스트 크기를 설정 합니다. 
   - `textSizeDIP()` : DIP단위의 텍스트 크기를 설정 합니다. 
   - `textSizeDIPResId()` : DIP단위의 텍스트 크기 Dimen resource id를 설정 합니다. 
   - `textSizePX()` : PX단위의 텍스트 크기를 설정 합니다. 
   - `textSizePT()` : PT단위의 텍스트 크기를 설정 합니다. 
  - `textColor(int color)` : 텍스트의 컬러를 설정 합니다.
   - `textColorRes(int colorResId)` : 텍스트의 컬러로 설정할 Color resource id를 설정 합니다. 
  - `textBackgroundColor(), textBackgroundColorRes()` : 텍스트에 배경 색을 설정 합니다. 
  - `bold(), unBold()` : 텍스트를 굵게 만들거나 원래 상태로 만들어 줍니다. 
  - `italic(), unItalic()` : 텍스트를 기울게 만들거나 원래 상태로 만들어 줍니다. 
  - `underline(), unUnderline()` : 텍스트에 밑줄을 그려 주거나 원래 상태로 만들어 줍니다. 
  - `strike(), unStrike()` : 텍스트에 취소선을 그려 주거나 원래 상태로 만들어 줍니다. 
  - `superScript(), unSuperScript()` : 텍스트에 upper속성을 가해주거나 원래 상태로 만들어 줍니다. 
  - `subScript(), unSubScript()` : 텍스트에 lower속성을 가해주거나 원래 상태로 만들어 줍니다. 
  - `click(ClickableSpan click)` : 텍스트에 클릭 이벤트를 부여 합니다. 
   - `clickHighlighjtColor()` : 클릭 이벤트를 받는 텍스트가 터치 되었을때의 컬러를 설정 합니다. 
   - `linkClickEnable(), linkClickDisable()` : 클릭 이벤트의 적용 유무를 설정 합니다. 
   - `linkTextColor()` : 클릭 이벤트를 받는 텍스트의 컬러를 변경 해 줍니다. 

#### ver 0.0.2  
- `blurMaskFilter(float radius)` : 텍스트에 블러마스크 필터 효과를 줍니다. 텍스트 폰트 사이즈가 작을 경우 블러효과가 갱신되지 않을 수 있습니다.  
 - `blurMaskFilter(float, BlurMaskFilter.Blur blurStyle)` : 텍스트에 블러마스크필터 효과를 줍니다. blurstyle을 바꿀 수 있습니다. (`NORMAL`, `SOLID`, `OUTER`, `INNER`)  
- `embossMaskFilter(float[] direction, float ambient, float specular, float blurRadius)` : 엠보싱효과를 텍스트에 줍니다. 
- `typeFaceFamily(String fontFamily)` : 텍스트의 폰트를 변경 합니다. 폰트가 디바이스에서 설치 되어 있어야 합니다.  
 
#### ver 0.0.3
 - `findRegExStrings(String regEx, SwClickableSpan clickSpan)` : 사용자 정규식을 통해 단어를 찾고 링크를 설정 합니다.
  - `findSharpTags(SwClickableSpan)` : '#'으로 시작되는 단어를 찾고 링크를 설정 합니다. 
  - `findAtTags(SwClickableSpan)` : '@'으로 시작되는 단어를 찾고 링크를 설정 합니다. 
  - `findURLstrings(SwClickableSpan)` : URL으로 구성된 단어를 찾고 링크를 설정 합니다. 
 - `SwClickableSpan` class
  - `ClickableSpan`을 상속한 클래스로서 터치 여부에 따라 텍스트 컬러, 배경 컬러등을 설정 할 수 있습니다. 
   
#### ver 1.0.0
 - `findRegExStrings(String regEx, ColorInt)` : 사용자 정규식을 통해 단어를 찾고 링크를 설정 합니다.
  - `findSharpTags(ColorInt)` : '#'으로 시작되는 단어를 찾고 링크를 설정 합니다. 
  - `findAtTags(ColorInt)` : '@'으로 시작되는 단어를 찾고 링크를 설정 합니다. 
  - `findURLstrings(ColorInt)` : URL으로 구성된 단어를 찾고 링크를 설정 합니다.  

---
### TODO List  
- ver 0.0.2
  - [x] Blur Mask Filter Span
  - [x] Emboss Mask Filter Span 
  - [x] Scale X Span 
  - [x] typeFace Span

- ver 0.0.3
  - [x] '#', '@' 태그 링크 (style, click envent)
  - [x] 사용자 `Pattern` 적용 (style, click envent)
  
- ver 1.0.0
  - [x] '#', '@', 사용자 패턴 적용된 텍스트에 `SwClickableSpan`이 아닌 텍스트 컬러 지정할 수 있게 설정. 
   
- ver 1.0.1
  - [ ] image span, 미구현된 span들 추가. 
  - [ ] bug fix
  
---
### 1. install
 아래의 내용을 app의 `build.gradle`에 추가 해 주세요. 
```groovy
dependencies {
  compile 'kr.swkang.spannabletextview:libspannabletextview:0.0.3'
}
```  
   
---
### 2. use guide
 [Sample Activity](https://github.com/ksu3101/SpannableTextView/blob/master/app/src/main/java/kr/swkang/sample/SampleMainActivity.java)  
 - `ClickableSpan`의 경우 밑줄이 자동으로 생기는데 이를 없애기 위해선 아래와 같이 구현된 `ClickableSpan`내에서 `updateDrawState()`메소드를 재정의 하여 `TextPaint`객체의 `setUnderlineText(false)`메소드를 호출 해야 합니다. 이때 패러미터로 `false`를 주면 됩니다. 이 경우, `unUnderLine()`메소드를 통해서 밑줄이 사라지진 않습니다. 
 - 기본 텍스트뷰의 폰트 사이즈는 xml layout에서 설정한 텍스트 폰트 사이즈 입니다. 만약 xml layout에서 textSize항목을 설정 하지 않았다면 안드로이드의 기본 폰트 사이즈가 적용 됩니다. 

#### 2.1 사용 예
```xml
   <kr.swkang.spannabletextview.SpannableTextView
      android:id="@+id/stv_1"
      android:layout_width="match_parent"
      android:layout_height="wrap_content" />
```
```java
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
                // normal text color, pressed text color, pressed bg color
                new SwClickableSpan(Color.BLACK, Color.RED, Color.LTGRAY) {
                  @Override
                  public void onClick(View widget) {
                    Toast.makeText(SampleMainActivity.this, "# Tag clicked..", Toast.LENGTH_SHORT).show();
                  }
                }
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
```

---
### license
```
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 1):
 * <burkdog34@gmail.com> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return KangSung-Woo
 * ----------------------------------------------------------------------------
```
