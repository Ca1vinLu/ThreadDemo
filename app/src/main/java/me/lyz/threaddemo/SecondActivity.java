package me.lyz.threaddemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.lyz.threaddemo.fragment.TestFragment;

/**
 * @author LYZ 2018.09.06
 */
public class SecondActivity extends BaseActivity {
    private TestFragment mTestFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTestFragment = TestFragment.newInstance();

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, mTestFragment, TestFragment.TAG)
                        .commit();
            }
        });

        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .remove(mTestFragment)
                        .commit();
            }
        });

        findViewById(R.id.btn_attach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .attach(mTestFragment)
                        .commit();
            }
        });

        findViewById(R.id.btn_detach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .detach(mTestFragment)
                        .commit();
            }
        });
    }
}
