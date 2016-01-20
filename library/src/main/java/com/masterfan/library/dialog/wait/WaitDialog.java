package com.masterfan.library.dialog.wait;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.masterfan.library.R;

/**
 * 自定义窗口
 * <p>使用示例：</p>
 * <pre>
 */
public class WaitDialog extends Dialog {

	public WaitDialog(Context context, int theme) {
        super(context, theme);
    }
    public WaitDialog(Context context) {
        super(context);
    }
    
    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {
    	
        private Context context;
        private String title;
        private String message;
        
        
        private String positiveButtonText = null;
        private String negativeButtonText = null;
        
        private OnClickListener positiveButtonClickListener, negativeButtonClickListener;
        
        //
        public void setPositiveButtonClickListener(
				OnClickListener positiveButtonClickListener) {
			this.positiveButtonClickListener = positiveButtonClickListener;
		}

        //
		public void setNegativeButtonClickListener(
				OnClickListener negativeButtonClickListener) {
			this.negativeButtonClickListener = negativeButtonClickListener;
		}

		//
		public Builder(Context context) {
            this.context = context;
        }
        
        /**
         * Set the Dialog message from String
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        
        /**
         * Set the Dialog message from resource
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }
        
        /**
         * Set the Dialog title from resource
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }
        
        /**
         * Set the Dialog title from String
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set the positive button text and it's listener
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }
        
        
        /**
         * Set the negative button resource and it's listener
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }
        
        
        
        //---------------------------------------------------------
        // Create the custom dialog
        //---------------------------------------------------------
        public WaitDialog create() {
        	//
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            
            // instantiate the dialog with the custom Theme
            final WaitDialog dialog = new WaitDialog(context, R.style.Dialog);
            
            View layout = inflater.inflate(R.layout.dialog_wait, null);
           
            // set the content and title message
//            ((TextView) layout.findViewById(R.id.dialog_title_txt)).setText(title);

            //set the button text
//            if(negativeButtonText != null) {
//            	((TextView) layout.findViewById(R.id.dialog_cancel_txt)).setText(negativeButtonText);
//            	if(negativeButtonClickListener == null){
//                	negativeButtonClickListener = new OnClickListener() {
//
//    					@Override
//    					public void onClick(DialogInterface dialog, int which) {
//    						// TODO Auto-generated method stub
//    						dialog.dismiss();
//    						dialog = null;
//    					}
//    				};
//
//    				//
//    				layout.findViewById(R.id.dialog_cancel_txt).setOnClickListener(new View.OnClickListener(){
//
//         				@Override
//         				public void onClick(View v) {
//         					// TODO Auto-generated method stub
//         					negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
//         				}
//
//                     });
//                }else{
//                	 layout.findViewById(R.id.dialog_cancel_txt).setOnClickListener(new View.OnClickListener(){
//
//         				@Override
//         				public void onClick(View v) {
//         					// TODO Auto-generated method stub
//         					negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
//         				}
//
//                     });
//
//                	 //
//                	 if(dialog != null) dialog.dismiss();
//                }
//            }else{
//            	layout.findViewById(R.id.dialog_cancel_txt).setVisibility(View.GONE);
//            	layout.findViewById(R.id.dialog_confirm_txt).setBackgroundResource(R.drawable.dialog_single_gray_btn_bg);
//            }
//
//            //
//            if(positiveButtonText != null) ((TextView) layout.findViewById(R.id.dialog_confirm_txt)).setText(positiveButtonText);
//
//
//            //cancel button
//
//
//            //confirm button
//            layout.findViewById(R.id.dialog_confirm_txt).setOnClickListener(new View.OnClickListener(){
//
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
//
//					if(dialog != null)	dialog.dismiss();
//				}
//
//            });
//
            dialog.setContentView(layout);
            return dialog;
        }
        
    }
   
}
