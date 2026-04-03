import InputField from "components/fields/InputField";
import Checkbox from "components/checkbox";
import { useNavigate } from "react-router-dom";

export default function SignIn() {
  const navigate = useNavigate();

  const handleSignIn = () => {
    navigate("/console/default"); 
  };

  return (
    <div className="flex h-full w-full items-center justify-start px-2 md:pl-12 lg:pl-20">
      <div className="flex w-full max-w-full flex-col xl:max-w-[420px]">
        <div className="w-full text-left">
          <h4 className="mb-2.5 text-4xl font-bold text-black dark:text-white">
            Sign In
          </h4>
          <p className="mb-9 text-base text-gray-500">
            Sign in to access LIFE console
          </p>
        </div>
        <div className="w-full">
          <InputField
            variant="auth"
            extra="mb-4"
            label="Email*"
            placeholder="erlangga@openlife.id"
            id="email"
            type="text"
          />

          <InputField
            variant="auth"
            extra="mb-4"
            label="Password*"
            placeholder="••••••••"
            id="password"
            type="password"
          />

          <div className="mb-4 flex items-center justify-between px-2">
            <div className="flex items-center">
              <Checkbox />
              <p className="ml-2 text-sm font-medium text-black dark:text-white">
                Keep me logged In
              </p>
            </div>
          </div>
          <button 
            onClick={handleSignIn}
            className="mt-2 w-full rounded-lg bg-blue-800 py-[12px] text-base font-bold text-white transition duration-200 hover:bg-black active:bg-gray-900 shadow-lg shadow-blue-900/20"
          >
            Sign In
          </button>
        </div>
      </div>
    </div>
  );
}