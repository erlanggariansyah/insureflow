import InputField from "components/fields/InputField";
import Checkbox from "components/checkbox";
import { useNavigate } from "react-router-dom";
import logoShort from "../../assets/img/logo_short.png";
import { BsMicrosoft } from "react-icons/bs";

export default function SignIn() {
  const navigate = useNavigate();

  const handleSignIn = () => {
    navigate("/console/default"); 
  };

  return (
    <div className="mt-8 mb-16 flex h-full w-full items-center justify-center px-4 md:mx-0 md:px-0 lg:mb-10 lg:items-center lg:justify-start">
      <div className="mt-[3vh] w-full max-w-full flex-col items-center lg:pl-16 xl:max-w-[440px]">
        
        {/* Animated Enterprise Logo Header */}
        <div className="mb-10 flex items-center gap-4 transition-all hover:scale-[1.01]">
          <div className="flex h-14 w-14 items-center justify-center rounded-2xl bg-gradient-to-br from-blue-600 to-blue-400 shadow-xl shadow-blue-500/30">
            <img src={logoShort} alt="LifeConsole Logo" className="h-9 w-9 object-contain" />
          </div>
          <div className="flex flex-col">
            <div className="font-poppins text-3xl font-extrabold uppercase leading-tight tracking-wide text-navy-700 dark:text-white">
              Life<span className="font-light text-blue-500">Console</span>
            </div>
            <p className="text-[10px] font-bold tracking-[0.25em] text-gray-400 uppercase mt-0.5">Core Platform</p>
          </div>
        </div>

        <h4 className="mb-2 text-4xl font-extrabold tracking-tight text-navy-700 dark:text-white">
          Welcome Back
        </h4>
        <p className="mb-8 text-sm font-medium text-gray-500 dark:text-gray-400">
          Enter your corporate credentials to launch the workspace.
        </p>

        {/* Input Forms */}
        <InputField
          variant="auth"
          extra="mb-5"
          label="Corporate Email*"
          placeholder="agent@openlife.id"
          id="email"
          type="text"
        />

        <InputField
          variant="auth"
          extra="mb-5"
          label="Password*"
          placeholder="••••••••"
          id="password"
          type="password"
        />

        {/* Utility Links */}
        <div className="mb-7 flex items-center justify-between px-1">
          <div className="flex items-center">
            <Checkbox />
            <p className="ml-2 text-sm font-semibold text-navy-700 dark:text-white">
              Remember me
            </p>
          </div>
          <a
            className="text-sm font-bold text-blue-600 hover:text-blue-500 dark:text-blue-400 transition-colors"
            href="#forgot"
          >
            Forgot Password?
          </a>
        </div>

        {/* Primary Action */}
        <button
          onClick={handleSignIn}
          className="w-full flex items-center justify-center gap-2 rounded-xl bg-gradient-to-r from-blue-600 to-blue-400 py-3.5 text-sm font-bold text-white shadow-xl shadow-blue-500/30 transition-all hover:scale-[1.02] hover:shadow-blue-500/40 active:scale-95"
        >
          Secure Sign In
        </button>

        {/* Divider */}
        <div className="mt-8 flex items-center gap-4">
          <div className="h-px w-full bg-gray-200 dark:bg-navy-700" />
          <p className="text-[11px] font-bold text-gray-400 uppercase tracking-widest whitespace-nowrap">Enterprise SSO</p>
          <div className="h-px w-full bg-gray-200 dark:bg-navy-700" />
        </div>

        {/* Secondary Action */}
        <div className="mt-6">
          <button 
            onClick={handleSignIn}
            className="flex w-full items-center justify-center gap-3 rounded-xl border border-gray-200 bg-white py-3 text-sm font-bold text-navy-700 shadow-sm transition-all hover:-translate-y-0.5 hover:bg-gray-50 hover:shadow-md dark:border-navy-700 dark:bg-navy-800 dark:text-white dark:hover:bg-navy-700"
          >
            <BsMicrosoft className="h-5 w-5 text-[#00a4ef]" />
            Continue with Azure AD
          </button>
        </div>

        <p className="mt-10 text-center text-xs font-medium text-gray-400">
          By signing in, you agree to our{" "}
          <a href="#terms" className="font-bold text-navy-700 hover:text-blue-500 dark:text-white">Terms of Protocol</a> &{" "}
          <a href="#privacy" className="font-bold text-navy-700 hover:text-blue-500 dark:text-white">Data Privacy</a>.
        </p>

      </div>
    </div>
  );
}