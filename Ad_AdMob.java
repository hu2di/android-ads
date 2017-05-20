compile 'com.google.android.gms:play-services-ads:10.2.0'

<string name="id_native_result_unit">ca-app-pub-9414566302380491/2411858161</string>
<string name="banner_ad_unit_id">ca-app-pub-3940256099942544/6300978111</string>
<string name="interstitial_ad_unit_id">ca-app-pub-3940256099942544/1033173712</string>

xmlns:ads="http://schemas.android.com/apk/res-auto"

<com.google.android.gms.ads.AdView
        android:id="@+id/adView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_alignParentBottom="true"
        ads:adSize="SMART_BANNER"
        ads:adUnitId="@string/banner_ad_unit_id"/>

private void initBanner() {
    final AdView mAdView = (AdView) findViewById(R.id.adView);
    
    mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                mAdView.setVisibility(View.GONE);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
    });
    
    AdRequest adRequest = new AdRequest.Builder().build();
    mAdView.loadAd(adRequest);
}

private InterstitialAd mInterstitialAd;

private void initInterstitialAd() {
	mInterstitialAd = new InterstitialAd(this);
	mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));

	mInterstitialAd.setAdListener(new AdListener() {
        @Override
        public void onAdClosed() {
            requestNewInterstitial();
        }
    });

    requestNewInterstitial();
}

private void requestNewInterstitial() {
    AdRequest adRequest = new AdRequest.Builder()
        .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
        .build();

    mInterstitialAd.loadAd(adRequest);
}
         
private void showInterstitial() {
    if (mInterstitialAd.isLoaded()) {
        mInterstitialAd.show();
    }
}