/*
 *				Twidere - Twitter client for Android
 * 
 * Copyright (C) 2012 Mariotaku Lee <mariotaku.lee@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.activity;

import org.mariotaku.twidere.R;
import org.mariotaku.twidere.fragment.WebViewFragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.Window;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.CroutonStyle;

public class BrowserActivity extends BaseDialogWhenLargeActivity {

	private Uri mUri = Uri.parse("about:blank");

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
			case MENU_HOME:
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		requestSupportWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mUri = getIntent().getData();
		if (mUri == null) {
			Crouton.showText(this, R.string.error_occurred, CroutonStyle.ALERT);
			finish();
			return;
		}
		final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		final Fragment fragment = Fragment.instantiate(this, WebViewFragment.class.getName());
		final Bundle bundle = new Bundle();
		bundle.putString(INTENT_KEY_URI, mUri.toString());
		fragment.setArguments(bundle);
		ft.replace(R.id.main, fragment);
		ft.commit();
	}
}
