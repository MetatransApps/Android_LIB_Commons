package org.metatrans.commons.main;


import org.metatrans.commons.R;
import org.metatrans.commons.app.Application_Base;
import org.metatrans.commons.cfg.colours.IConfigurationColours;
import org.metatrans.commons.ui.ButtonAreaClick;
import org.metatrans.commons.ui.IButtonArea;
import org.metatrans.commons.ui.TextArea;
import org.metatrans.commons.ui.utils.DrawingUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;


public class View_Result extends View {


	private static final int MARGIN1 = 15;

	private RectF rectf_main;

	private RectF rectf_banner;

	private RectF rectf_info;
	private RectF rectf_label_outcome;
	private RectF rectf_data_outcome;
	private RectF rectf_label_mode;
	private RectF rectf_data_mode;
	private RectF rectf_label_column_your;
	private RectF rectf_label_column_best;

	private RectF rectf_label_correct;
	private RectF rectf_label_incorrect;
	private RectF rectf_label_time;

	private RectF rectf_data_correct_your;
	private RectF rectf_data_incorrect_your;
	private RectF rectf_data_time_your;

	private RectF rectf_data_correct_best;
	private RectF rectf_data_incorrect_best;
	private RectF rectf_data_time_best;

	private RectF rectf_buttons;
	private RectF rectf_button_moregames;
	private RectF rectf_button_newgame;
	private RectF rectf_button_back;

	private RectF rectf_leaderboards;
	private RectF rectf_invitefriends;

	private TextArea textarea_info;
	private TextArea textarea_data_outcome;
	private TextArea textarea_label_outcome;
	private TextArea textarea_label_mode;
	private TextArea textarea_data_mode;
	private TextArea textarea_label_column_your;
	private TextArea textarea_label_column_best;

	private TextArea textarea_label_1;
	private TextArea textarea_label_2;
	private TextArea textarea_label_3;

	private TextArea textarea_data_1_your;
	private TextArea textarea_data_2_your;
	private TextArea textarea_data_3_your;

	private TextArea textarea_data_1_best;
	private TextArea textarea_data_2_best;
	private TextArea textarea_data_3_best;

	private IButtonArea buttonarea_moregames;
	private IButtonArea buttonarea_newgame;
	private IButtonArea buttonarea_back;

	protected Paint paint_background;

	private IConfigurationColours coloursCfg;


	private boolean is_new_record;
	private boolean  show_mode;
	private String modeName;

	private String[] labels;
	private String[] data_your;
	private String[] data_best;


	public View_Result(Context context,
			boolean _is_new_record,
			boolean _show_mode,
			String _modeName,
			String[] _labels, String[] _data_your, String[] _data_best) {

		super(context);

		coloursCfg = Application_Base.getInstance().getColoursCfg();

		is_new_record = _is_new_record;
		show_mode = _show_mode;
		modeName = _modeName;

		labels = _labels;
		data_your = _data_your;
		data_best = _data_best;

		rectf_main 	= new RectF();

		rectf_banner = new RectF();

		rectf_info = new RectF();

		rectf_label_outcome = new RectF();
		rectf_data_outcome = new RectF();
		rectf_label_mode = new RectF();
		rectf_data_mode = new RectF();
		rectf_label_column_your = new RectF();
		rectf_label_column_best = new RectF();

		rectf_label_correct = new RectF();
		rectf_label_incorrect = new RectF();
		rectf_label_time = new RectF();

		rectf_data_correct_your = new RectF();
		rectf_data_incorrect_your = new RectF();
		rectf_data_time_your = new RectF();

		rectf_data_correct_best = new RectF();
		rectf_data_incorrect_best = new RectF();
		rectf_data_time_best = new RectF();

		rectf_buttons = new RectF();

		rectf_button_back = new RectF();

		rectf_invitefriends = new RectF();
		rectf_button_moregames = new RectF();
		rectf_button_newgame = new RectF();

		rectf_leaderboards = new RectF();


		paint_background = new Paint();
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		initializeDimensions();

		this.setMeasuredDimension( (int) (rectf_main.right - rectf_main.left), (int) (rectf_main.bottom - rectf_main.top) );
	}


	private void initializeDimensions() {

		int main_width = getMeasuredWidth();
		int main_height = getMeasuredHeight();

		float segments_main = 4 + labels.length + (show_mode ? 1 : 0);

		rectf_main.left = 0;
		rectf_main.top = 0;
		rectf_main.right = main_width;
		rectf_main.bottom = main_height;

		float sections_count = 8.875f;

		int section_height = (int) (main_height / sections_count);

		rectf_banner.left = 0;
		rectf_banner.right = main_width - 0;
		rectf_banner.top = rectf_main.top;
		if (getMeasuredHeight() > getMeasuredWidth()) {
			rectf_banner.bottom = rectf_banner.top + section_height;
		} else {
			rectf_banner.bottom = rectf_banner.top + (int) (1.75f * section_height);
		}

		float row_height = (rectf_main.bottom - rectf_banner.bottom) / segments_main;
		float column_width = (rectf_main.right - rectf_main.left) / 3;

		rectf_info.left = rectf_main.left + MARGIN1;
		rectf_info.top = rectf_banner.bottom + section_height / 2;
		rectf_info.right = rectf_main.right - MARGIN1;
		rectf_info.bottom = rectf_info.top + row_height - MARGIN1;

		rectf_label_outcome.left = rectf_main.left + MARGIN1;
		rectf_label_outcome.top = rectf_info.bottom + MARGIN1;
		rectf_label_outcome.right = rectf_label_outcome.left + column_width - MARGIN1;
		rectf_label_outcome.bottom = rectf_label_outcome.top + row_height - MARGIN1;

		rectf_data_outcome.left = rectf_label_outcome.right + MARGIN1;
		rectf_data_outcome.top = rectf_info.bottom + MARGIN1;
		rectf_data_outcome.right = rectf_main.right - MARGIN1;
		rectf_data_outcome.bottom = rectf_data_outcome.top + row_height - MARGIN1;

		rectf_label_mode.left = rectf_main.left + MARGIN1;
		rectf_label_mode.top = rectf_data_outcome.bottom + MARGIN1;
		rectf_label_mode.right = rectf_label_mode.left + column_width - MARGIN1;
		rectf_label_mode.bottom = rectf_label_mode.top + row_height - MARGIN1;

		rectf_data_mode.left = rectf_label_mode.right + MARGIN1;
		rectf_data_mode.top = rectf_label_mode.top;
		rectf_data_mode.right = rectf_main.right - MARGIN1;//rectf_data_mode.left + column_width - MARGIN1;
		rectf_data_mode.bottom = rectf_label_mode.bottom;

		if (show_mode) {
			rectf_label_column_your.left = rectf_data_mode.left;
			rectf_label_column_your.top = rectf_data_mode.bottom + section_height / 2 + MARGIN1;
			rectf_label_column_your.right = rectf_label_column_your.left + column_width - MARGIN1;
			rectf_label_column_your.bottom = rectf_label_column_your.top + row_height - MARGIN1;
		} else {
			rectf_label_column_your.left = rectf_main.left + column_width + MARGIN1;
			rectf_label_column_your.top = rectf_data_outcome.bottom + section_height / 2 + MARGIN1;
			rectf_label_column_your.right = rectf_label_column_your.left + column_width - MARGIN1;
			rectf_label_column_your.bottom = rectf_label_column_your.top + row_height - MARGIN1;
		}

		rectf_label_column_best.left = rectf_label_column_your.right + MARGIN1;
		rectf_label_column_best.top = rectf_label_column_your.top;
		rectf_label_column_best.right = rectf_main.right - MARGIN1;//rectf_data_mode.left + column_width - MARGIN1;
		rectf_label_column_best.bottom = rectf_label_column_your.bottom;

		rectf_label_correct.left = rectf_main.left + MARGIN1;
		rectf_label_correct.top = rectf_label_column_your.bottom + MARGIN1;
		rectf_label_correct.right = rectf_label_correct.left + column_width - MARGIN1;
		rectf_label_correct.bottom = rectf_label_correct.top + row_height - MARGIN1;

		rectf_data_correct_your.left = rectf_label_correct.right + MARGIN1;
		rectf_data_correct_your.top = rectf_label_correct.top;
		rectf_data_correct_your.right = rectf_data_correct_your.left + column_width - MARGIN1;
		rectf_data_correct_your.bottom = rectf_label_correct.bottom;

		rectf_data_correct_best.left = rectf_data_correct_your.right + MARGIN1;
		rectf_data_correct_best.top = rectf_label_correct.top;
		rectf_data_correct_best.right = rectf_data_correct_best.left + column_width - MARGIN1;
		rectf_data_correct_best.bottom = rectf_label_correct.bottom;

		rectf_label_incorrect.left = rectf_main.left + MARGIN1;
		rectf_label_incorrect.top = rectf_label_correct.bottom + MARGIN1;
		rectf_label_incorrect.right = rectf_label_incorrect.left + column_width - MARGIN1;
		rectf_label_incorrect.bottom = rectf_label_incorrect.top + row_height - MARGIN1;

		rectf_data_incorrect_your.left = rectf_label_incorrect.right + MARGIN1;
		rectf_data_incorrect_your.top = rectf_label_incorrect.top;
		rectf_data_incorrect_your.right = rectf_data_incorrect_your.left + column_width - MARGIN1;
		rectf_data_incorrect_your.bottom = rectf_label_incorrect.bottom;

		rectf_data_incorrect_best.left = rectf_data_incorrect_your.right + MARGIN1;
		rectf_data_incorrect_best.top = rectf_label_incorrect.top;
		rectf_data_incorrect_best.right = rectf_data_incorrect_best.left + column_width - MARGIN1;
		rectf_data_incorrect_best.bottom = rectf_label_incorrect.bottom;

		rectf_label_time.left = rectf_main.left + MARGIN1;
		rectf_label_time.top = rectf_data_incorrect_your.bottom + MARGIN1;
		rectf_label_time.right = rectf_label_time.left + column_width - MARGIN1;
		rectf_label_time.bottom = rectf_label_time.top + row_height - MARGIN1;

		rectf_data_time_your.left = rectf_label_time.right + MARGIN1;
		rectf_data_time_your.top = rectf_label_time.top;
		rectf_data_time_your.right = rectf_data_time_your.left + column_width - MARGIN1;
		rectf_data_time_your.bottom = rectf_label_time.bottom;

		rectf_data_time_best.left = rectf_data_time_your.right + MARGIN1;
		rectf_data_time_best.top = rectf_label_time.top;
		rectf_data_time_best.right = rectf_data_time_best.left + column_width - MARGIN1;
		rectf_data_time_best.bottom = rectf_label_time.bottom;

		/*rectf_buttons.left = rectf_canvas.left;
		rectf_buttons.top = rectf_canvas.top - row_height - 2 * MARGIN1;
		rectf_buttons.right = rectf_canvas.right;
		rectf_buttons.bottom = rectf_canvas.top - 1 * MARGIN1;*/

		/*rectf_invitefriends.left = rectf_canvas.left + MARGIN1;
		rectf_invitefriends.top = rectf_buttons.top + MARGIN1;
		rectf_invitefriends.right = rectf_invitefriends.left + column_width - MARGIN1;
		rectf_invitefriends.bottom = rectf_invitefriends.top + row_height - MARGIN1;*/

		/*rectf_button_newgame.left = rectf_invitefriends.right + MARGIN1;
		rectf_button_newgame.top = rectf_buttons.top + MARGIN1;
		rectf_button_newgame.right = rectf_button_newgame.left + column_width - MARGIN1;
		rectf_button_newgame.bottom = rectf_button_newgame.top + row_height - MARGIN1;*/

		/*rectf_button_moregames.left = rectf_button_newgame.right + MARGIN1;
		rectf_button_moregames.top = rectf_buttons.top + MARGIN1;
		rectf_button_moregames.right = rectf_button_moregames.left + column_width - MARGIN1;
		rectf_button_moregames.bottom = rectf_button_moregames.top + row_height - MARGIN1;*/

		/*rectf_button_back.left = (rectf_canvas.right - rectf_canvas.left) / 2 - column_width / 2;
		rectf_button_back.top = rectf_canvas.bottom + MARGIN1;
		rectf_button_back.right = (rectf_canvas.right - rectf_canvas.left) / 2 + column_width / 2;
		rectf_button_back.bottom = rectf_button_back.top + row_height;*/


		textarea_info = new TextArea(rectf_info, "  " + getContext().getString(R.string.label_result_info) + "  ",
				coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Background());

		textarea_label_outcome = new TextArea(rectf_label_outcome, "  " + getContext().getString(R.string.label_result_outcome) + "  ",
				coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White());

		if (is_new_record) {

			textarea_data_outcome = new TextArea(rectf_data_outcome, "  " + getContext().getString(R.string.label_result_status_newrecord) + "  ",
					coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_ValidSelection());
		} else {
			String header = "  " + getContext().getString(R.string.label_result_status_youfinished) + "  ";
			textarea_data_outcome = new TextArea(rectf_data_outcome, header,
					coloursCfg.getColour_Square_Black(), Color.WHITE);
		}

		if (show_mode) {
			textarea_label_mode = new TextArea(rectf_label_mode, "  " + getContext().getString(R.string.label_mode) + "  ",
					coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White());

			textarea_data_mode = new TextArea(rectf_data_mode, "  " + modeName + "  ",
					coloursCfg.getColour_Square_Black(), Color.WHITE);
		}

		textarea_label_column_your = new TextArea(rectf_label_column_your, "  " + getContext().getString(R.string.label_your) + "  ",
				coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Background());

		textarea_label_column_best = new TextArea(rectf_label_column_best, "  " + getContext().getString(R.string.label_best) + "  ",
				coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Background());


		textarea_label_1 = new TextArea(rectf_label_correct, "  " + labels[0] + "  ",
				coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White());

		textarea_data_1_your = new TextArea(rectf_data_correct_your, "" + data_your[0],
				coloursCfg.getColour_Square_Black(), Color.WHITE);

		textarea_data_1_best = new TextArea(rectf_data_correct_best, "" + data_best[0],
				coloursCfg.getColour_Square_Black(), Color.WHITE);

		if (labels.length > 1) {

			if (true) {
				throw new IllegalStateException();
			}

			textarea_label_2 = new TextArea(rectf_label_incorrect, "  " + labels[1] + "  ",
					coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White());

			//if (gameData.count_answered - gameData.count_correct == 0) {
				textarea_data_2_your = new TextArea(rectf_data_incorrect_your, "" + data_your[1],
						coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_MarkingSelection());
			//} else {
				//textarea_data_2_your = new TextArea(rectf_data_incorrect_your, "" + (gameData.count_answered - gameData.count_correct),
					//	coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_InvalidSelection());
			//}

			//if (bestResult.count_incorrect == 0) {
				textarea_data_2_best = new TextArea(rectf_data_incorrect_best, "" + data_best[1],
						coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_MarkingSelection());
			//} else {
			//	textarea_data_2_best = new TextArea(rectf_data_incorrect_best, "" + bestResult.count_incorrect,
			//			coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_InvalidSelection());
			//}
		}

		if (labels.length > 2) {
			textarea_label_3 = new TextArea(rectf_label_time, "  " + labels[2] + "  ",
					coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White());

			textarea_data_3_your = new TextArea(rectf_data_time_your, data_your[2],
					coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_MarkingSelection());

			textarea_data_3_best = new TextArea(rectf_data_time_best, data_best[2],
					coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_MarkingSelection());
		}


		buttonarea_moregames =  new ButtonAreaClick(rectf_button_moregames, "  " + getContext().getString(R.string.label_moregames) + "  ",
				//coloursCfg.getColour_Delimiter(), coloursCfg.getColour_Square_White(), coloursCfg.getColour_Square_ValidSelection());
				coloursCfg.getColour_Square_ValidSelection(), coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_MarkingSelection());

		buttonarea_newgame = new ButtonAreaClick(rectf_button_newgame, "  " + getContext().getString(R.string.new_game_fulltext) + "  ",
				coloursCfg.getColour_Square_ValidSelection(), coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_MarkingSelection());

		buttonarea_back =  new ButtonAreaClick(rectf_button_back, "  "  + getContext().getString(R.string.button_back) +  "  ",
				coloursCfg.getColour_Square_ValidSelection(), coloursCfg.getColour_Square_Black(), coloursCfg.getColour_Square_MarkingSelection());


		rectf_leaderboards.left 	= rectf_button_newgame.left;
		rectf_leaderboards.top 		= rectf_buttons.top - row_height - 2 * MARGIN1;
		rectf_leaderboards.right 	= rectf_button_newgame.right;
		rectf_leaderboards.bottom 	= rectf_leaderboards.top + row_height + MARGIN1;

		float width = rectf_leaderboards.right - rectf_leaderboards.left;
		rectf_leaderboards.left = rectf_leaderboards.left - width / 5;
		//rectf_leaderboards.top = rectf_leaderboards.top;
		rectf_leaderboards.right = rectf_leaderboards.right + width / 5;
		//rectf_leaderboards.bottom = rectf_leaderboards.bottom + (rectf_leaderboards.bottom - rectf_leaderboards.top) / 4;

	}


	public RectF getRectangle_LeaderBoards() {
		return rectf_leaderboards;
	}


	public RectF getRectangle_InviteFriends() {
		return rectf_invitefriends;
	}


	/*
	private String toDoubleDigit(int number) {
		String result = "";
		if (number < 10) {
			result = "0" + number;
		} else {
			result += number;
		}
		return result;
	}
	*/

	@Override
	protected synchronized void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		paint_background.setColor(coloursCfg.getColour_Background());
		//DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_main);
		canvas.drawRect(0, 0, rectf_main.width(), rectf_main.height(), paint_background);

		paint_background.setColor(coloursCfg.getColour_Delimiter());
		//DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_canvas);

		paint_background.setColor(coloursCfg.getColour_Delimiter());
		DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_buttons);

		textarea_info.draw(canvas);

		textarea_label_outcome.draw(canvas);
		textarea_data_outcome.draw(canvas);

		if (show_mode) {
			textarea_label_mode.draw(canvas);
			textarea_data_mode.draw(canvas);
		}

		textarea_label_column_your.draw(canvas);
		textarea_label_column_best.draw(canvas);

		if (textarea_label_1 != null) textarea_label_1.draw(canvas);
		if (textarea_label_2 != null) textarea_label_2.draw(canvas);
		if (textarea_label_3 != null) textarea_label_3.draw(canvas);

		if (textarea_data_1_your != null) textarea_data_1_your.draw(canvas);
		if (textarea_data_2_your != null) textarea_data_2_your.draw(canvas);
		if (textarea_data_3_your != null) textarea_data_3_your.draw(canvas);

		if (textarea_data_1_best != null) textarea_data_1_best.draw(canvas);
		if (textarea_data_2_best != null) textarea_data_2_best.draw(canvas);
		if (textarea_data_3_best != null) textarea_data_3_best.draw(canvas);

		//paint_background.setColor(coloursCfg.getColour_Square_MarkingSelection());
		//DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_button_moregames);
		//DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_button_newgame);
		//DrawingUtils.drawRoundRectangle(canvas, paint_background, rectf_button_back);

		buttonarea_moregames.draw(canvas);
		buttonarea_newgame.draw(canvas);
		buttonarea_back.draw(canvas);
	}


	public boolean isOverButton_Back(float x, float y) {
		return rectf_button_back.contains(x, y);
	}


	public void selectButton_Back() {
		buttonarea_back.select();
		invalidate();
	}


	public void deselectButton_Back() {
		buttonarea_back.deselect();
		invalidate();
	}


	public boolean isOverButton_New(float x, float y) {
		return rectf_button_newgame.contains(x, y);
	}


	public void selectButton_New() {
		buttonarea_newgame.select();
		invalidate();
	}


	public void deselectButton_New() {
		buttonarea_newgame.deselect();
		invalidate();
	}


	public boolean isOverButton_MoreGames(float x, float y) {
		return rectf_button_moregames.contains(x, y);
	}


	public void selectButton_MoreGames() {
		buttonarea_moregames.select();
		invalidate();
	}


	public void deselectButton_MoreGames() {
		buttonarea_moregames.deselect();
		invalidate();
	}
}
