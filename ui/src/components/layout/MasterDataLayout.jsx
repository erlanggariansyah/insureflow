import React from "react";

/**
 * MasterDataLayout
 * A unified layout for all Master Data pages, featuring a left Workspace sidebar
 * and a main content area.
 * 
 * @param {string} title - Title of the Workspace sidebar
 * @param {React.ReactNode} children - Main content (usually a table)
 * @param {Array} actions - List of action objects for the sidebar
 */
export default function MasterDataLayout({ title = "Workspace", children, actions = [] }) {
  // Split actions into sidebar items and footer buttons
  const sidebarItems = actions.filter(a => !a.isPrimary);
  const primaryActions = actions.filter(a => a.isPrimary);

  return (
    <div className="flex h-[calc(100vh-120px)] w-full gap-5 mt-5 overflow-hidden">

      {/* Left Workspace Sidebar - Styled like Canvas */}
      <div className="flex w-[280px] flex-col rounded-2xl bg-gradient-to-b from-white to-gray-50 border border-gray-100 p-5 shadow-xl shadow-gray-200/50 dark:border-navy-700 dark:from-navy-800 dark:to-navy-900 dark:shadow-navy-900 overflow-hidden">
        <div className="mb-6 flex flex-col">
          <h2 className="text-lg font-bold text-navy-700 dark:text-white">{title}</h2>
        </div>

        {/* List Items - Matching draggable look */}
        <div className="flex-1 space-y-3 overflow-y-auto pr-1 custom-scrollbar">
          {sidebarItems.map((action, i) => (
            <div
              key={i}
              onClick={action.onClick}
              className={`group flex cursor-pointer items-start gap-3 rounded-xl border border-transparent bg-white p-3 shadow-sm transition-all hover:-translate-y-0.5 hover:shadow-md dark:bg-navy-800 dark:hover:bg-navy-800/80`}
            >
              <div className={`mt-0.5 flex items-center justify-center rounded-lg p-2 ${action.color || 'text-blue-500 bg-blue-50 dark:bg-blue-900/30'} transition-transform group-hover:scale-110`}>
                {action.icon}
              </div>
              <div className="flex flex-col">
                <span className="text-sm font-bold text-navy-700 dark:text-white">{action.label}</span>
                <span className="text-[10px] font-medium text-gray-400">{action.desc}</span>
              </div>
            </div>
          ))}
        </div>

        {/* Footer Buttons - Exactly like Canvas footer */}
        <div className="mt-4 flex flex-col gap-3 border-t border-gray-200 pt-5 dark:border-navy-700">
          {primaryActions.map((action, i) => (
            <button
              key={i}
              onClick={action.onClick}
              className={`flex w-full items-center justify-center gap-2 rounded-xl p-3 text-sm font-bold transition-all active:scale-95 ${action.variant === 'secondary'
                ? "border border-gray-200 bg-white text-navy-700 hover:bg-gray-50 dark:border-navy-600 dark:bg-navy-800 dark:text-white"
                : "bg-gradient-to-r from-blue-600 to-blue-400 text-white shadow-lg shadow-blue-500/30 hover:scale-[1.02] hover:shadow-blue-500/50"
                }`}
            >
              {action.icon}
              {action.label}
            </button>
          ))}
          {!primaryActions.length && (
            <div className="rounded-xl bg-blue-50/50 p-3 dark:bg-navy-900/50">
              <p className="text-[10px] font-medium text-blue-600 dark:text-blue-400 italic">
                Select an operation to begin.
              </p>
            </div>
          )}
        </div>
      </div>

      {/* Main Content Area - Full width table view */}
      <div className="flex-grow overflow-hidden relative">
        <div className="h-full w-full overflow-y-auto custom-scrollbar relative z-10">
          {children}
        </div>
      </div>

    </div>
  );
}
