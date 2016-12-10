(ns chalk.toast)

(defn toast
  ([msgvec]
   (let [[type msg] msgvec]
     (toast type msg)))
  ([type msg]
   (let [toast-fn (case type
                    :info js/toastr.info
                    :success js/toastr.success
                    :warning js/toastr.warning
                    :error js/toastr.error
                    js/toastr.info)]
     (toast-fn msg))))

(defn toast-n [msgs]
  (doseq [msg msgs]
    (toast msg)))
